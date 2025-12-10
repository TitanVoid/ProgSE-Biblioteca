package controllers.prestiti;
import controllers.BaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code
    }

    public void addLoans(){
        List<Prestito> listPrestiti = biblioteca.getPrestiti().getPrestiti();
        prestiti.setAll(listPrestiti);
    }

    private void showNewWindow(String viewName, String title) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(viewName)));
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

            Stage stage = (Stage) item.getParentPopup().getOwnerWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        } catch (NullPointerException | IOException ex) {
            showErrorAlert("Error", "Could Not Find " + mode + " FXML");
        }
    }
}