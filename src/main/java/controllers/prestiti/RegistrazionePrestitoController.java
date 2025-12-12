package controllers.prestiti;
import controllers.BaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import models.FormatoCampiErratoException;
import models.ISBN;
import models.Matricola;
import models.libri.Libro;
import models.prestiti.Prestito;
import models.utenti.Utente;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistrazionePrestitoController extends BaseController implements Initializable {

    @FXML
    private TextField matricola;
    @FXML
    private TextField isbn;
    @FXML
    private TextField dataInizio;
    @FXML
    private TextField dataScadenza;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Prevent user from typing non-dates
        dataInizio.setDisable(true);
        dataInizio.setText(LocalDate.now().toString());
        dataScadenza.setText(LocalDate.now().plusWeeks(2).toString());
    }

    @FXML
    private void onConfirm(){
        // Confirmation logic
        String matricolaUtente = matricola.getText();
        String isbnLibro = isbn.getText();
        String dataScadenzaPrestito = dataScadenza.getText();

        String mask = "";
        if (!biblioteca.getUtenti().esisteChiave(new Matricola(matricolaUtente))){
            mask += "0";
        } else {
            mask += "1";
        }

        if (!biblioteca.getLibri().esisteChiave(new ISBN(isbnLibro))){
            mask += "0";
        } else {
            mask += "1";
        }

        try {
            Prestito.verificaDataScadenza(dataScadenzaPrestito);
            mask += "1";
        } catch (FormatoCampiErratoException ex){
            mask += "0";
        }

        if (!mask.equals("111")){
            String[] campi = {"Matricola - Non esiste un utente registrato con questa matricola", "ISBN - Non esiste un libro registrato con questo ISBN", "Data di Scadenza - Deve aderire al formato AAAA-MM-GG"};
            StringBuilder sb = new StringBuilder("Attenzione, i seguenti campi sono errati:\n");
            for (int i = 0; i < mask.length(); i++) {
                if (mask.charAt(i) == '0') sb.append("\n").append(campi[i]);
            }
            showWarningAlert("Campi Errati", sb.toString());
            return;
        }

        biblioteca.getPrestiti().aggiungi(new Prestito(new Matricola(matricolaUtente), new ISBN(isbnLibro), LocalDate.now(), LocalDate.parse(dataScadenzaPrestito)));
        PrestitiController prestitiController = (PrestitiController) parentController;
        prestitiController.refreshLoans();
        Stage stage = (Stage) matricola.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancel(){
        // Cancellation logic
        Stage stage = (Stage) matricola.getScene().getWindow();
        stage.close();
    }
}