package controllers.utenti;
import controllers.BaseController;
import controllers.libri.LibriController;
import controllers.prestiti.PrestitiController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.prestiti.Prestito;
import models.utenti.Utente;
import javafx.beans.binding.Bindings;


import java.io.IOException;
import java.net.URL;
import java.util.*;

public class UtentiController extends BaseController implements Initializable {

    private final ObservableList<Utente> utenti = FXCollections.observableArrayList();
    private final Map<String, Stage> windows = new HashMap<>();

    @FXML
    private TableView<Utente> tableUtenti;
    @FXML
    private TableColumn<Utente, String> nomeClm;
    @FXML
    private TableColumn<Utente, String> cognomeClm;
    @FXML
    private TableColumn<Utente, String> matricolaClm;
    @FXML
    private TableColumn<Utente, String> emailClm;
    @FXML
    private TableColumn<Utente, String> prestitiAttiviClm;
    @FXML
    private TextField searchBar;
    @FXML
    private Button modificaButton;
    @FXML
    private Button eliminaButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code
        SelectionModel<Utente> selectionModel= tableUtenti.getSelectionModel();
        modificaButton.disableProperty().bind(Bindings.isNull(selectionModel.selectedItemProperty()));
        eliminaButton.disableProperty().bind(Bindings.isNull(selectionModel.selectedItemProperty()));

        nomeClm.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cognomeClm.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        matricolaClm.setCellValueFactory(cell -> {
            String matricolaFinale = cell.getValue().getMatricolaUtente().getMatricola();
            return new SimpleStringProperty(matricolaFinale);
        });
        emailClm.setCellValueFactory(new PropertyValueFactory<>("email"));
        prestitiAttiviClm.setCellValueFactory(cell -> {
            StringBuilder prAt = new StringBuilder();
            for (Prestito a : cell.getValue().getPrestitiAttivi()){
                prAt.append(a.toString()).append(", ");
            }
            return new SimpleStringProperty(prAt.toString());
        });

        tableUtenti.setItems(utenti);
    }

    public void refreshUsers(){
        List<Utente> listUtenti = biblioteca.getUtenti().getListaUtenti();
        utenti.setAll(listUtenti);
    }

    private void showNewWindow(String viewName, String title) {
        if (windows.containsKey(viewName)) {
            Stage stage = windows.get(viewName);
            stage.toFront();
            return;
        }
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(viewName)));
            Parent root = loader.load();
            BaseController controller = loader.getController();
            controller.setParentController(this);
            controller.setBiblioteca(biblioteca);
            if (controller instanceof ModificaUtenteController){
                ModificaUtenteController cont = (ModificaUtenteController) controller;
                cont.loadUser(getSelectedUser());
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            windows.put(viewName, stage);
            stage.setOnHidden(e -> windows.remove(viewName));
            stage.showAndWait();
        } catch (IOException | NullPointerException ex) {
            showErrorAlert("Error", "Could Not Find FXML at " + viewName);
        }
    }

    public Utente getSelectedUser(){
        return tableUtenti.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void onAddUser(){
        showNewWindow("/views/utenti/AggiuntaUtenteView.fxml", "Aggiunta Utente");
    }

    @FXML
    private void onModifyUser(){
        showNewWindow("/views/utenti/ModificaUtenteView.fxml", "Modifica Utente");
    }

    @FXML
    private void onDeleteUser(){
        showNewWindow("/views/AvvisoConfermaRimozioneView.fxml", "Rimozione Utente");
    }

    @FXML
    private void onSearchUser(){
        String input = searchBar.getText().trim();
        if (input.isEmpty()) {
            refreshUsers();
            return;
        }
        List<Utente> listUtenti = biblioteca.getUtenti().ricercaUtenti(input);
        utenti.setAll(listUtenti);
    }

    @FXML
    private void onChangeViewMode(ActionEvent event){
        MenuItem item = (MenuItem) event.getSource();
        String mode = (String) item.getUserData();

        FXMLLoader loader = null;
        if (mode.equals("LIBRI")) {
            loader = new FXMLLoader(getClass().getResource("/views/libri/LibriView.fxml"));
        } else if (mode.equals("PRESTITI")) {
            loader = new FXMLLoader(getClass().getResource("/views/prestiti/PrestitiView.fxml"));
        }

        try{
            Parent root = loader.load();
            BaseController controller = loader.getController();
            controller.setBiblioteca(this.biblioteca);  // Pass the same instance
            if (controller instanceof LibriController){
                LibriController libriController = (LibriController) controller;
                libriController.refreshBooks();
            } else if (controller instanceof PrestitiController) {
                PrestitiController prestitiController = (PrestitiController) controller;
                prestitiController.refreshLoans();
            }
            Stage stage = (Stage) item.getParentPopup().getOwnerWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        } catch (NullPointerException | IOException ex) {
            showErrorAlert("Error", "Could Not Find " + mode + " FXML");
        }
    }
}