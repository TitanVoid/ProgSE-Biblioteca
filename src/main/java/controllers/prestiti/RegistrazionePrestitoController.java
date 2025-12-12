package controllers.prestiti;
import controllers.BaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import models.libri.Libro;
import models.utenti.Utente;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistrazionePrestitoController extends BaseController implements Initializable {

    @FXML
    private ComboBox<Utente> utenteCombo;
    @FXML
    private ComboBox<Libro> libroCombo;
    @FXML
    private DatePicker dataScadenza;

    private ObservableList<Utente> utenti;
    private ObservableList<Libro> libri;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Prevent user from typing non-dates
        dataScadenza.setEditable(false);
        dataScadenza.setValue(LocalDate.now().plusWeeks(2));

        utenti = FXCollections.observableArrayList();
        libri = FXCollections.observableArrayList();

        utenteCombo.setConverter(new StringConverter<Utente>() {
            @Override
            public String toString(Utente utente) {
                if (utente == null) return "";
                return utente.getMatricolaUtente().getMatricola() + " - " + utente.getNome() + " " + utente.getCognome();
            }

            @Override
            public Utente fromString(String string) {
                return null;
            }
        });

        libroCombo.setConverter(new StringConverter<Libro>() {
            @Override
            public String toString(Libro libro) {
                if (libro == null) return "";
                return libro.getCodiceISBNLibro().getCodiceISBN() + " - " + libro.getTitolo();
            }

            @Override
            public Libro fromString(String string) {
                return null;
            }
        });

        utenteCombo.setItems(utenti);
        libroCombo.setItems(libri);
    }

    @FXML
    private void onUserType(){
        // User has selected
        if (utenteCombo.getValue() != null) return;
        utenteCombo.hide();
        if (utenteCombo.getEditor().getText().isEmpty()) {
            utenti.setAll(biblioteca.getUtenti().getListaUtenti());
        } else{
            utenti.setAll(biblioteca.getUtenti().ricercaUtenti(utenteCombo.getEditor().getText()));
        }
        int size = utenti.size();
        if (size > 5) size = 5;
        utenteCombo.setVisibleRowCount(size);
        if (!utenti.isEmpty()) utenteCombo.show();
    }

    @FXML
    private void onUserSelect(){
        System.out.println("Utente selezionato: " + utenteCombo.getSelectionModel().getSelectedItem());
        utenteCombo.getEditor().setEditable(false);
    }

    @FXML
    private void onBookSelect(){

    }

    @FXML
    private void onBookType(){
        // User has selected
        if (libroCombo.getValue() != null) return;
        libroCombo.hide();
        if (libroCombo.getEditor().getText().isEmpty()) {
            libri.setAll(biblioteca.getLibri().getListaLibri());
        } else {
            libri.setAll(biblioteca.getLibri().ricercaLibri(libroCombo.getEditor().getText()));
        }
        int size = libri.size();
        if (size > 5) size = 5;
        libroCombo.setVisibleRowCount(size);
        if (!libri.isEmpty()) libroCombo.show();
    }

    @FXML
    private void onConfirm(){
        // Confirmation logic

    }

    @FXML
    private void onCancel(){
        // Cancellation logic
        Stage stage = (Stage) utenteCombo.getScene().getWindow();
        stage.close();
    }
}