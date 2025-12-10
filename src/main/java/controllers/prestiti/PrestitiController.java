package controllers.prestiti;
import controllers.BaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.prestiti.Prestito;

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

    }

    @FXML
    private void onReturnLoan(){

    }

    @FXML
    private void onFilterLoans(){
    }

    @FXML
    private void onChangeViewMode(){

    }
}