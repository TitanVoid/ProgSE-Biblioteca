package controllers.libri;
import controllers.BaseController;
import controllers.prestiti.PrestitiController;
import controllers.utenti.UtentiController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.libri.Autore;
import models.libri.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.utenti.Utente;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class LibriController extends BaseController implements Initializable{

    private final ObservableList<Libro> libri = FXCollections.observableArrayList();
    private final Map<String, Stage> windows = new HashMap<>();

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
    @FXML
    private Button modificaButton;
    @FXML
    private Button eliminaButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code
        SelectionModel<Libro> selectionModel= tableLibri.getSelectionModel();
        modificaButton.disableProperty().bind(Bindings.isNull(selectionModel.selectedItemProperty()));
        eliminaButton.disableProperty().bind(Bindings.isNull(selectionModel.selectedItemProperty()));

        titleClm.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        authorsClm.setCellValueFactory(cell -> {
            StringJoiner authors = new StringJoiner(", ");
            for (Autore a : cell.getValue().getAutori()){
                authors.add(a.toString());
            }
            return new SimpleStringProperty(authors.toString());
        });
        publishYearClm.setCellValueFactory(new PropertyValueFactory<>("annoPubblicazione"));
        idClm.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCodiceISBNLibro().getCodiceISBN()));
        copiesClm.setCellValueFactory(new PropertyValueFactory<>("copieDisponibili"));

        titleClm.prefWidthProperty().bind(tableLibri.widthProperty().multiply(0.30));
        authorsClm.prefWidthProperty().bind(tableLibri.widthProperty().multiply(0.35));
        publishYearClm.prefWidthProperty().bind(tableLibri.widthProperty().multiply(0.10));
        idClm.prefWidthProperty().bind(tableLibri.widthProperty().multiply(0.15));
        copiesClm.prefWidthProperty().bind(tableLibri.widthProperty().multiply(0.10));

        tableLibri.setItems(libri);
    }

    public Libro getSelectedBook(){
        return tableLibri.getSelectionModel().getSelectedItem();
    }


    public void refreshBooks(){
        List<Libro> listLibri = biblioteca.getLibri().getListaLibri();
        libri.setAll(listLibri);
    }

    private void showNewWindow(String viewName, String title) {
        if (windows.containsKey(viewName)) {
            Stage stage = windows.get(viewName);
            stage.toFront();
            return;
        }
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(viewName)));
            Parent root = loader.load();
            BaseController controller = loader.getController();
            controller.setParentController(this);
            controller.setBiblioteca(biblioteca);
            if(controller instanceof ModificaLibroController){
                ModificaLibroController cont = (ModificaLibroController) controller;
                cont.loadBook(getSelectedBook());
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            windows.put(viewName, stage);
            stage.setOnHidden(e -> windows.remove(viewName));
            stage.showAndWait();
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
            refreshBooks();
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
            if(controller instanceof UtentiController){
                UtentiController utentiController = (UtentiController) controller;
                utentiController.refreshUsers();
            } else if (controller instanceof PrestitiController) {
                PrestitiController prestitiController = (PrestitiController) controller;
                prestitiController.refreshLoans();
            }
            Stage stage = (Stage) item.getParentPopup().getOwnerWindow();
            Scene scene = stage.getScene();
            scene.setRoot(root);
        } catch (NullPointerException | IOException ex) {
            showErrorAlert("Error", "Error Loading " + mode + " FXML");
        }
    }
}