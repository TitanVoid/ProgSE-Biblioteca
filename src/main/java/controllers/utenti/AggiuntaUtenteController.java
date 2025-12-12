package controllers.utenti;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.FormatoCampiErratoException;
import models.Matricola;
import models.utenti.Utente;
import controllers.BaseController;
import models.utenti.UtenteGiaPresenteException;

import java.net.URL;
import java.util.ResourceBundle;

public class AggiuntaUtenteController extends BaseController implements Initializable {

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
        // Confirmation logic
        try {
            String n = nome.getText();
            String c = cognome.getText();
            String m = matricola.getText();
            String e = email.getText();

            Utente.verificaUtente(n, c,m,e );

            Utente u = new Utente(n, c, new Matricola(m), e);
            biblioteca.getUtenti().aggiungi(u);

            UtentiController utentiController = (UtentiController) parentController;
            utentiController.refreshUsers();

            Stage stage = (Stage) nome.getScene().getWindow();
            stage.close();

        }catch(FormatoCampiErratoException ex){
            String maschera = ex.getMessage();
            System.out.println(maschera);
            String[] campi = {"Nome - Da 3-25 caratteri alfabetici", "Cognome - Da 3-25 caratteri alfabetici", "Matricola - Numero a 10 cifre", "Email - <*>.<*>@studenti.unisa.it"};
            StringBuilder sb = new StringBuilder("Attenzione, i seguenti campi devono avere il formato corretto:\n");
            for (int i = 0; i < maschera.length(); i++) {
                if (maschera.charAt(i) == '0') sb.append("\n").append(campi[i]).append(" ");
            }
            showWarningAlert("Campi Errati", sb.toString());
        }catch(UtenteGiaPresenteException ex){
            showWarningAlert("Utente Gia Presente", "Un Utente con questa matricola esiste già nell'archivio!");
        }catch(RuntimeException ex){
            showErrorAlert("Error", ex.toString());
        }
        
    }

    @FXML
    private void onCancel(){
        // Cancellation logic
        Stage stage = (Stage) nome.getScene().getWindow();
        stage.close();
    }
}