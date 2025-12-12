package controllers.utenti;
import controllers.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.FormatoCampiErratoException;
import models.utenti.Utente;

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
            utenteModificato=utentiController.getSelectedUser();

            String nomeUtente = nome.getText();
            String cognomeUtente = cognome.getText();
            //String matricolaText = matricola.getText();
            String emailUtente = email.getText();

            Utente.verificaUtente(nomeUtente, cognomeUtente, utentiController.getSelectedUser().getMatricolaUtente().getMatricola() , emailUtente);

            utenteModificato.setNome(nomeUtente);
            utenteModificato.setCognome(cognomeUtente);
            utenteModificato.setEmail(emailUtente);


            biblioteca.getUtenti().modifica(utentiController.getSelectedUser(), utenteModificato);
            utentiController.refreshUsers();

            Stage stage = (Stage) nome.getScene().getWindow();
            stage.close();

        }catch (FormatoCampiErratoException ex){
            String maschera = ex.getMessage();
            System.out.println(maschera);
            String[] campi = {"Nome - Da 3-25 caratteri alfabetici", "Cognome - Da 3-25 caratteri alfabetici", "Matricola - Numero a 10 cifre", "Email - <*>.<*>@studenti.unisa.it"};
            StringBuilder sb = new StringBuilder("Attenzione, i seguenti campi devono avere il formato corretto:\n");
            for (int i = 0; i < maschera.length(); i++) {
                if (maschera.charAt(i) == '0') sb.append("\n").append(campi[i]).append(" ");
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