package controllers.libri;

import controllers.BaseController;
import controllers.utenti.UtentiController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import models.libri.Libro;
import models.utenti.Utente;

import java.net.URL;
import java.util.ResourceBundle;

public class RimozioneLibroController extends BaseController {

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
            LibriController libriController = (LibriController) parentController;
            Libro libroDaEliminare = libriController.getSelectedBook();

            biblioteca.getLibri().rimuovi(libroDaEliminare);
            libriController.refreshBooks();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //Stage stage = (Stage) confermaBtn.getScene().getWindow();
        //stage.close();
        
    }

    @FXML
    private void onCancel(){
        // Cancellation logic
    }
}