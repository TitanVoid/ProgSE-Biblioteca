
package controllers;

import controllers.libri.LibriController;
import controllers.prestiti.PrestitiController;
import controllers.utenti.UtentiController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.libri.Libro;
import models.prestiti.Prestito;
import models.utenti.Utente;

public class RimozioneController extends BaseController{

    @FXML
    private Button confermaBtn;

    public void onConfirm(){
        try {
            BaseController rimuoviController = parentController;
            if(rimuoviController instanceof UtentiController){
                Utente utenteDaEliminare = ((UtentiController) rimuoviController).getSelectedUser();
                biblioteca.getUtenti().rimuovi(utenteDaEliminare);
                ((UtentiController) rimuoviController).refreshUsers();
            }else if(rimuoviController instanceof LibriController) {
                Libro libroDaEliminare = ((LibriController) rimuoviController).getSelectedBook();
                biblioteca.getLibri().rimuovi(libroDaEliminare);
                ((LibriController) rimuoviController).refreshBooks();
            }else if(rimuoviController instanceof PrestitiController) {
                Prestito prestitoDaEliminare = ((PrestitiController) rimuoviController).getSelectedLoan();
                biblioteca.getPrestiti().rimuovi(prestitoDaEliminare);
                ((PrestitiController) rimuoviController).refreshLoans();
            }
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