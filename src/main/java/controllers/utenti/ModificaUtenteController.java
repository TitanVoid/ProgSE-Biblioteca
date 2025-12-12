package controllers.utenti;
import controllers.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.FormatoCampiErratoException;
import models.utenti.Utente;
import models.utenti.UtenteGiaPresenteException;

import java.net.URL;
import java.util.ResourceBundle;

public class ModificaUtenteController extends BaseController implements Initializable {


    private Utente utenteModificato;

    @FXML
    private TextField nome;
    @FXML
    private TextField cognome;
    @FXML
    private TextField matricola;
    @FXML
    private TextField email;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code
    }

    @FXML
    private void onConfirm(){
        // Save changes logic

        try {
            UtentiController utentiController = (UtentiController) parentController;
            utenteModificato=utentiController.getSelectedUtente();

            String nomeUtente = nome.getText();
            String cognomeUtente = cognome.getText();
            //String matricolaText = matricola.getText();
            String emailUtente = email.getText();

            Utente.verificaUtente(nomeUtente, cognomeUtente, utentiController.getSelectedUtente().getMatricolaUtente().getMatricola() , emailUtente);

            utenteModificato.setNome(nomeUtente);
            utenteModificato.setCognome(cognomeUtente);
            utenteModificato.setEmail(emailUtente);


            biblioteca.getUtenti().modifica(utentiController.getSelectedUtente(), utenteModificato);
            utentiController.addUtente();

            Stage stage = (Stage) nome.getScene().getWindow();
            stage.close();

        }catch (FormatoCampiErratoException ex){
            String maschera = ex.getMessage();
            System.out.println(maschera);
            String[] campi = {"Nome", "Cognome", "Matricola", "Email"};
            StringBuilder sb = new StringBuilder("Attenzione, i seguenti campi sono errati: ");
            for (int i = 0; i < maschera.length(); i++) {
                if (maschera.charAt(i) == '0') sb.append(campi[i]).append(" ");
            }
            showWarningAlert("Campi Errati", sb.toString());
        }catch(RuntimeException ex){
            showErrorAlert("Error", ex.toString());
        }
    }

    @FXML
    private void onCancel(){
        // Cancel changes logic
        Stage stage = (Stage) nome.getScene().getWindow();
        stage.close();
    }
}