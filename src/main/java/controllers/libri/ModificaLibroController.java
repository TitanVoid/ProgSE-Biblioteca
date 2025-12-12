package controllers.libri;
import controllers.BaseController;
import controllers.utenti.UtentiController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.FormatoCampiErratoException;
import models.ISBN;
import models.libri.Libro;
import models.utenti.Utente;

public class ModificaLibroController extends BaseController {

    @FXML
    private TextField titolo;
    @FXML
    private TextField autori;
    @FXML
    private TextField codiceISBN;
    @FXML
    private TextField annoPubblicazione;
    @FXML
    private TextField copieDisponibili;

    public void loadBook(Libro libro) {

        titolo.setText(libro.getTitolo());
        autori.setText(libro.getAutori().toString());
        codiceISBN.setText(libro.getCodiceISBNLibro().getCodiceISBN());
        annoPubblicazione.setText(String.valueOf(libro.getAnnoPubblicazione()));
        copieDisponibili.setText(String.valueOf(libro.getCopieDisponibili()));

    }

    @FXML
    private void onConfirm(){
        // Save changes logic
        try {
            LibriController libriController = (LibriController) parentController;
            Libro libroDaModificare = libriController.getSelectedBook();

            String titoloLibro = titolo.getText();
            String autoriLibro = autori.getText();
            String  codiceISBNLibro = codiceISBN.getText();
            String annoPubblicazioneLibro = annoPubblicazione.getText();
            String copieDisponibiliLibro = copieDisponibili.getText();

            Libro.verificaLibro(autoriLibro,titoloLibro,annoPubblicazioneLibro,libroDaModificare.getCodiceISBNLibro().getCodiceISBN(),copieDisponibiliLibro);
            Libro libroModificato =  new Libro(titoloLibro,Integer.parseInt(annoPubblicazioneLibro),new ISBN(codiceISBNLibro),Integer.parseInt(copieDisponibiliLibro),autoriLibro);

            biblioteca.getLibri().modifica(libroDaModificare, libroModificato);
            libriController.refreshBooks();

            Stage stage = (Stage) titolo.getScene().getWindow();
            stage.close();

        }catch (FormatoCampiErratoException ex){
            String maschera= ex.getMessage();
            System.out.println(maschera);
            String[] campi= {"Titolo - Massimo 100 caratteri", "Autore/i - Nome Cognome, Nome Cognome ...", "ISBN - 13 cifre numeriche (Rispetta standard ISBN reale)", "Copie Disponibili - Numero intero tra 0 e 100", "Anno Pubblicazione - Numero intero tra 0 e anno corrente"};
            StringBuilder sb =  new StringBuilder("Attenzione, i seguenti campi devono avere il formsto corretto:\n");
            for (int i = 0; i < maschera.length(); i++) {
                if(maschera.charAt(i)== '0') sb.append("\n").append(campi[i]);
            }
            showWarningAlert("Campi Errati",sb.toString());
        }catch (RuntimeException ex){
            showErrorAlert("Error",ex.toString());
        }
    }

    @FXML
    private void onCancel(){
        // Cancel changes logic
        Stage stage = (Stage) titolo.getScene().getWindow();
        stage.close();
    }



}