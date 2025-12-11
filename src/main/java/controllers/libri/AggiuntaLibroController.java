package controllers.libri;
import controllers.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Biblioteca;
import models.FormatoCampiErrato;
import models.ISBN;
import models.libri.Autore;
import models.libri.Libri;
import models.libri.Libro;
import models.libri.LibroGiaPresenteException;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AggiuntaLibroController extends BaseController {

    @FXML
    private TextField titolo;
    @FXML
    private TextField autori;
    @FXML
    private TextField copieDisponibili;
    @FXML
    private TextField ISBN;
    @FXML
    private TextField annoPubblicazione;



    @FXML
    private void onConfirm() {
        try {
            // Confirmation logic
            String t = titolo.getText();
            String a = autori.getText();
            String isbn = ISBN.getText();
            String c = copieDisponibili.getText();
            String anno = annoPubblicazione.getText();

            Libro.verificaLibro(a, t, anno, isbn, c);

            Libro l = new Libro(t,Integer.parseInt(annoPubblicazione.getText()),new ISBN(isbn),Integer.parseInt(copieDisponibili.getText()),a);
            biblioteca.getLibri().aggiungi(l);

            LibriController libriController = (LibriController) parentController;
            libriController.addBooks();
            Stage stage = (Stage) titolo.getScene().getWindow();
            stage.close();
        } catch (FormatoCampiErrato ex){
            String maschera = ex.getMessage();
            System.out.println(maschera);
            String[] campi = {"Titolo", "Autore/i", "ISBN", "Copie Disponibili", "Anno Pubblicazione"};
            StringBuilder sb = new StringBuilder("Attenzione, i seguenti campi sono errati: ");
            for (int i = 0; i < maschera.length(); i++) {
                if (maschera.charAt(i) == '0') sb.append(campi[i]).append(" ");
            }
            showWarningAlert("Campi Errati", sb.toString());
        } catch (LibroGiaPresenteException ex){
            showWarningAlert("Libro Gia Presente", "Un Libro con questo ISBN esiste già nell'archivio!");
        } catch (RuntimeException e) {
            showErrorAlert("Error", e.toString());
        }
    }

    @FXML
    private void onCancel(){
        // Cancellation logic
        Stage stage = (Stage) titolo.getScene().getWindow();
        stage.close();
    }
}