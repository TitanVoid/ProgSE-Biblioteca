package controllers.libri;
import controllers.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Biblioteca;
import models.ISBN;
import models.libri.Autore;
import models.libri.Libri;
import models.libri.Libro;


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
        // Confirmation logic
        String t = titolo.getText();
        String a = autori.getText();
        String isbn = ISBN.getText();
        List<Autore> al = new ArrayList<Autore>();
        String[] x = a.split(",");

        for (int i = 0; i < x.length; i++) {
            String[] y = x[i].split(" ");
            String nome = y[0];
            String cognome = y[1];
            al.add(new Autore(nome, cognome));
        }
        try {

            int c = Integer.parseInt(copieDisponibili.getText());
            int anno = Integer.parseInt(annoPubblicazione.getText());

            Libro.verificaLibro(al, t, anno, isbn, c); /// da implementare in caso di return false 

        }catch(NumberFormatException e) {
            Logger.getLogger(AggiuntaLibroController.class.getName()).log(Level.SEVERE, null, e);
        }catch(RuntimeException e) {
            Logger.getLogger(AggiuntaLibroController.class.getName()).log(Level.SEVERE, null, e);
        }
        Libro l= new Libro(t,Integer.parseInt(annoPubblicazione.getText()),new ISBN(isbn),Integer.parseInt(copieDisponibili.getText()),al);

        try {
           biblioteca.getLibri().aggiungi(l);
        }catch(Exception e) {
            Logger.getLogger(AggiuntaLibroController.class.getName()).log(Level.SEVERE, null, e);
        }

        LibriController libriController = (LibriController) parentController;
        libriController.addBooks();
        Stage stage = (Stage) titolo.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void onCancel(){
        // Cancellation logic
        Stage stage = (Stage) titolo.getScene().getWindow();
        stage.close();
    }
}