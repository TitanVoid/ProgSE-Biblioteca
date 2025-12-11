package controllers.prestiti;
import controllers.BaseController;
import controllers.libri.LibriController;
import controllers.utenti.UtentiController;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.stage.Stage;
import models.prestiti.Prestito;
import models.servizi.Filtro;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.ResourceBundle;

public class PrestitiController extends BaseController implements Initializable {

    private final ObservableList<Prestito> prestiti = FXCollections.observableArrayList();

    @FXML
    private TableView<Prestito> prestitiTable;
    @FXML
    private TableColumn<Prestito, String> matricolaClm;
    @FXML
    private TableColumn<Prestito, String> isbnClm;
    @FXML
    private TableColumn<Prestito, String> dataInizioClm;
    @FXML
    private TableColumn<Prestito, String> dataScadenzaClm;
    @FXML
    private TableColumn<Prestito, String> dataRestituzioneClm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code
        matricolaClm.setCellValueFactory(cell -> {
            return new SimpleStringProperty(cell.getValue().getMatricolaUtente().getMatricola());
        });
        isbnClm.setCellValueFactory(cell -> {
            return new SimpleStringProperty(cell.getValue().getCodiceISBNLibro().getCodiceISBN());
        });
        dataInizioClm.setCellValueFactory(cell -> {
            return new SimpleStringProperty(cell.getValue().getDataInizio().toString());
        });
        dataScadenzaClm.setCellValueFactory(cell -> {
            return new SimpleStringProperty(cell.getValue().getDataScadenza().toString());
        });
        dataRestituzioneClm.setCellValueFactory(cell -> {
            return new SimpleStringProperty(cell.getValue().getDataRestituzione().toString());
        });
    }

    public void addLoans(){
        List<Prestito> listPrestiti = biblioteca.getPrestiti().getListaPrestiti();
        prestiti.setAll(listPrestiti);
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
            stage.show();
        } catch (IOException | NullPointerException ex) {
            showErrorAlert("Error", "Could Not Find FXML at " + viewName);
        }
    }

    @FXML
    private void onRegisterLoan() {
        showNewWindow("/views/prestiti/RegistrazionePrestitoView.fxml", "Registrazione Prestito");
    }

    @FXML
    private void onExtendLoan(){
        showNewWindow("/views/prestiti/EstensionePrestitoView.fxml", "Estensione Prestito");
    }

    @FXML
    private void onReturnLoan(){
        showNewWindow("/views/AvvisoConfermaRimozioneView.fxml", "Restituzione Prestito");
    }

    @FXML
    private void onFilterLoans(){
        /// ////////////////
        /// // Ricordare qua/////
        /// ////////////////////
        List<Prestito> listPrestiti = biblioteca.getPrestiti().filtra(Filtro.ATTIVI); //Capire come selezionare i filtri
        prestiti.setAll(listPrestiti);
    }

    @FXML
    private void onChangeViewMode(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        String mode = (String) item.getUserData();

        FXMLLoader loader = null;
        if (mode.equals("UTENTI")) {
            loader = new FXMLLoader(getClass().getResource("/views/utenti/UtentiView.fxml"));
        } else if (mode.equals("LIBRI")) {
            loader = new FXMLLoader(getClass().getResource("/views/libri/LibriView.fxml"));
        }

        try{
            Parent root = loader.load();
            BaseController controller = loader.getController();
            controller.setBiblioteca(this.biblioteca);  // Pass the same instance
            if (controller instanceof UtentiController){
                UtentiController utentiController = (UtentiController) controller;
                //utentiController.addUsers()
            } else if (controller instanceof LibriController){
                LibriController libriController = (LibriController) controller;
                libriController.addBooks();
            }
            Stage stage = (Stage) item.getParentPopup().getOwnerWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        } catch (NullPointerException | IOException ex) {
            showErrorAlert("Error", "Could Not Find " + mode + " FXML");
        }
    }
}