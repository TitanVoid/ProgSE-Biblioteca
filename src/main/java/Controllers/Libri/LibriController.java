package Controllers.Libri;
import Controllers.BaseController;
import Models.Libri.Libro;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Libro, String> colTitolo;
    @FXML
    private TableColumn<Libro, String> colAutori;
    @FXML
    private TableColumn<Libro, Integer> colAnno;
    @FXML
    private TableColumn<Libro, String> colCodice;
    @FXML
    private TableColumn<Libro, Integer> colCopie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code
        colTitolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        colAutori.setCellValueFactory(new PropertyValueFactory<>("autori"));
        colAnno.setCellValueFactory(new PropertyValueFactory<>("anno"));
        colCodice.setCellValueFactory(new PropertyValueFactory<>("codice"));
        colCopie.setCellValueFactory(new PropertyValueFactory<>("copie"));

        tableLibri.setItems(libri);
        libri.clear();
        List<Libro> listLibri = biblioteca.getLibri().getLibri();
        libri.addAll(listLibri);
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
        showNewWindow("/Views/Libri/AggiuntaLibroView.fxml", "Aggiunta Libro");
    }

    @FXML
    private void onModifyBook() throws IOException {
        showNewWindow("/Views/Libri/ModificaLibroView.fxml", "Modifica Libro");
    }

    @FXML
    private void onDeleteBook() throws IOException {
        showNewWindow("/Views/AvvisoConfermaRimozioneView.fxml", "Rimozione Libro");
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
            loader = new FXMLLoader(getClass().getResource("/Views/Utenti/UtentiView.fxml"));
        } else if (mode. equals("PRESTITI")) {
            loader = new FXMLLoader(getClass().getResource("/Views/Prestiti/PrestitiView.fxml"));
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