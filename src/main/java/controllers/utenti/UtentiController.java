package controllers.utenti;
import controllers.BaseController;
import controllers.libri.LibriController;
import controllers.prestiti.PrestitiController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.utenti.Utente;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class UtentiController extends BaseController implements Initializable {

    private final ObservableList<Utente> utenti = FXCollections.observableArrayList();

    @FXML
    private TableView<Utente> tableUtenti;
    @FXML
    private TableColumn<Utente, String> nomeClm;
    @FXML
    private TableColumn<Utente, String> cognomeClm;
    @FXML
    private TableColumn<Utente, Integer> matricolaClm;
    @FXML
    private TableColumn<Utente, String> prestitiAttiviClm;
    @FXML
    private TextField searchBar;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code
    }

    public void addUtente(){
        List<Utente> listUtenti = biblioteca.getUtenti().getListaUtenti();
        utenti.setAll(listUtenti);
    }

    private void showNewWindow(String viewName, String title) {
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(viewName)));
            Parent root = loader.load();
            BaseController controller = loader.getController();
            controller.setParentController(this);
            controller.setBiblioteca(biblioteca);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.showAndWait();
        } catch (IOException | NullPointerException ex) {
            showErrorAlert("Error", "Could Not Find FXML at " + viewName);
        }
    }

    @FXML
    private void onAddUser(){
        showNewWindow("/views/utenti/AggiuntaUtenteView.fxml", "Aggiunta Utente");
    }

    @FXML
    private void onModifyUser(){
        showNewWindow("/views/utenti/ModificaUtentiView.fxml", "Modifica Utente");
    }

    @FXML
    private void onDeleteUser(){
        showNewWindow("/views/AvvisoConfermaRimozioneView.fxml", "Rimozione Utente");
    }

    @FXML
    private void onSearchUser(){
        String input = searchBar.getText();
        if (input.isEmpty()) {
            addUtente();
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
                //libriController.addBooks();  /////<-------- Modificato
            } else if (controller instanceof PrestitiController) {
                PrestitiController prestitiController = (PrestitiController) controller;
                prestitiController.addLoans();
            }
            Stage stage = (Stage) item.getParentPopup().getOwnerWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        } catch (NullPointerException | IOException ex) {
            showErrorAlert("Error", "Could Not Find " + mode + " FXML");
        }
    }
}