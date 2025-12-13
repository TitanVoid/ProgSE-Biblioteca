package controllers.prestiti;
import controllers.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.FormatoCampiErratoException;
import models.ISBN;
import models.Matricola;
import models.prestiti.Prestito;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EstensionePrestitoController extends BaseController implements Initializable {

    @FXML
    private TextField matricola;
    @FXML
    private TextField isbn;
    @FXML
    private TextField dataScadenza;
    @FXML
    private TextField dataInizio;

    public void loadPrestito(Prestito prestito){
        matricola.setText(prestito.getMatricolaUtente().getMatricola());
        isbn.setText(prestito.getCodiceISBNLibro().getCodiceISBN());
        dataScadenza.setText(prestito.getDataScadenza().toString());
        dataInizio.setText(prestito.getDataInizio().toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code
        matricola.setDisable(true);
        isbn.setDisable(true);
        dataInizio.setDisable(true);
    }

    @FXML
    private void onConfirm(){
        // Save changes logic
        String data = dataScadenza.getText();
        try {
            Prestito.verificaDataScadenza(data);
        } catch (FormatoCampiErratoException e) {
            showWarningAlert("Campi Errati", "La data scadenza deve essere nel formato AAAA-MM-GG");
            return;
        }
        Prestito modificato = new Prestito(new Matricola(matricola.getText()), new ISBN(isbn.getText()), LocalDate.parse(dataInizio.getText()), LocalDate.parse(dataScadenza.getText()));
        PrestitiController prestitiController = (PrestitiController) parentController;
        biblioteca.getPrestiti().modifica(prestitiController.getSelectedLoan(), modificato);
        prestitiController.refreshLoans();
        Stage stage = (Stage) matricola.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancel(){
        // Cancel changes logic
        Stage stage = (Stage) matricola.getScene().getWindow();
        stage.close();
    }
}