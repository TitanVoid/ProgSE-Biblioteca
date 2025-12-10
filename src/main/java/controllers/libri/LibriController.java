package controllers.libri;
import controllers.BaseController;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.libri.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LibriController extends BaseController implements Initializable{

    private final ObservableList<Libro> libri = FXCollections.observableArrayList();

    @FXML
    private TableView<Libro> tableLibri;
    @FXML
    private TableColumn<Libro, String> titleClm;
    @FXML
    private TableColumn<Libro, String> authorsClm;
    @FXML
    private TableColumn<Libro, Integer> publishYearClm;
    @FXML
    private TableColumn<Libro, String> idClm;
    @FXML
    private TableColumn<Libro, Integer> copiesClm;
    @FXML
    private TextField searchBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code
        titleClm.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        authorsClm.setCellValueFactory(cell -> {
            return new SimpleStringProperty(cell.getValue().getAutori().toString());
        });
        publishYearClm.setCellValueFactory(new PropertyValueFactory<>("annoPubblicazione"));
        idClm.setCellValueFactory(cell -> {
            return new SimpleStringProperty(cell.getValue().getCodiceISBNLibro().getCodiceISBN());
        });
        copiesClm.setCellValueFactory(new PropertyValueFactory<>("copieDisponibili"));

        tableLibri.setItems(libri);
    }

    public void addBooks(){
        List<Libro> listLibri = biblioteca.getLibri().getLibri();
        libri.setAll(listLibri);
    }

    private void showNewWindow(String viewName, String title) {
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(viewName)));
            Parent root = loader.load();
            BaseController controller = loader.getController();
            controller.setParentController(this);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
        } catch (IOException | NullPointerException ex) {
            showErrorAlert("Error", "Could Not Find FXML at " + viewName);
        }
    }

    @FXML
    private void onAddBook() {
        showNewWindow("/views/libri/AggiuntaLibroView.fxml", "Aggiunta Libro");
    }

    @FXML
    private void onModifyBook() {
        showNewWindow("/views/libri/ModificaLibroView.fxml", "Modifica Libro");
    }

    @FXML
    private void onDeleteBook() {
        showNewWindow("/views/AvvisoConfermaRimozioneView.fxml", "Rimozione Libro");
    }

    @FXML
    private void onSearchBook(){
        String input = searchBar.getText();
        if (input.isEmpty()) {
            addBooks();
            return;
        }
        List<Libro> listLibri = biblioteca.getLibri().ricercaLibri(input);
        libri.setAll(listLibri);
    }

    @FXML
    private void onChangeViewMode(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        String mode = (String) item.getUserData();

        FXMLLoader loader = null;
        if (mode.equals("UTENTI")) {
            loader = new FXMLLoader(getClass().getResource("/views/utenti/UtentiView.fxml"));
        } else if (mode.equals("PRESTITI")) {
            loader = new FXMLLoader(getClass().getResource("/views/prestiti/PrestitiView.fxml"));
        }

        try{
            Parent root = loader.load();
            BaseController controller = loader.getController();
            controller.setBiblioteca(this.biblioteca);  // Pass the same instance

            Stage stage = (Stage) item.getParentPopup().getOwnerWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        } catch (NullPointerException | IOException ex) {
            showErrorAlert("Error", "Could Not Find " + mode + " FXML");
        }
    }
}