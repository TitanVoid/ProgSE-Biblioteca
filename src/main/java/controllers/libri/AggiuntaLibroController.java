package controllers.libri;
import controllers.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.FormatoCampiErratoException;
import models.ISBN;
import models.libri.Libro;
import models.libri.LibroGiaPresenteException;

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
            libriController.refreshBooks();
            Stage stage = (Stage) titolo.getScene().getWindow();
            stage.close();
        } catch (FormatoCampiErratoException ex){
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