package controllers.utenti;
import controllers.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.utenti.Utente;

import java.net.URL;
import java.util.ResourceBundle;

public class RimozioneUtenteController extends BaseController {

    @FXML
    private Button confermaBtn;

    /*
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code
    }

     */

    @FXML
    private void onConfirm(){
        // Deletion logic
        try {
            UtentiController utentiController = (UtentiController) parentController;
            Utente utenteDaEliminare = utentiController.getSelectedUser();

            biblioteca.getUtenti().rimuovi(utenteDaEliminare);
            utentiController.refreshUsers();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Stage stage = (Stage) confermaBtn.getScene().getWindow();
        stage.close();



    }

    @FXML
    private void onCancel(){
        // Cancellation logic
        Stage stage = (Stage) confermaBtn.getScene().getWindow();
        stage.close();
    }
}