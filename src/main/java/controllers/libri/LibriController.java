package controllers.libri;
import controllers.BaseController;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code
        /*titleClm.setCellValueFactory(new PropertyValueFactory<>("Titolo"));
        authorsClm.setCellValueFactory(cell -> {
            return new SimpleStringProperty(cell.getValue().getAutori().toString());
        });
        publishYearClm.setCellValueFactory(new PropertyValueFactory<>("anno"));
        idClm.setCellValueFactory(cell -> {
            return new SimpleStringProperty(cell.getValue().getCodiceISBNLibro();
        });
        copiesClm.setCellValueFactory(new PropertyValueFactory<>("copie"));

        tableLibri.setItems(libri);
        libri.clear();
        List<Libro> listLibri = biblioteca.getLibri().getLibri();
        libri.addAll(listLibri);*/
    }

    private void showNewWindow(String viewName, String title) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(viewName)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    @FXML
    private void onAddBook() throws IOException {
        showNewWindow("/views/libri/AggiuntaLibroView.fxml", "Aggiunta Libro");
    }

    @FXML
    private void onModifyBook() throws IOException {
        showNewWindow("/views/libri/ModificaLibroView.fxml", "Modifica Libro");
    }

    @FXML
    private void onDeleteBook() throws IOException {
        showNewWindow("/views/AvvisoConfermaRimozioneView.fxml", "Rimozione Libro");
    }

    @FXML
    private void onSearchBook(){
        System.out.println("Test");
    }

    @FXML
    private void onChangeViewMode(ActionEvent event) throws IOException {
        MenuItem item = (MenuItem) event.getSource();
        String mode = (String) item.getUserData();

        FXMLLoader loader = null;
        if (mode.equals("UTENTI")) {
            loader = new FXMLLoader(getClass().getResource("/views/utenti/UtentiView.fxml"));
        } else if (mode. equals("PRESTITI")) {
            loader = new FXMLLoader(getClass().getResource("/views/prestiti/PrestitiView.fxml"));
        }

        if (loader != null) {
            Parent root = loader.load();
            // Get the new controller and pass the Biblioteca instance
            BaseController controller = loader.getController();
            controller.setBiblioteca(this.biblioteca);  // Pass the same instance!

            Scene scene = new Scene(root);
            Stage stage = (Stage) item.getParentPopup().getOwnerWindow();
            stage.setScene(scene);
        }
    }
}