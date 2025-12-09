package Controllers.Libri;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LibriController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code
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
        Parent root = null;
        if (mode.equals("UTENTI")){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/Utenti/UtentiView.fxml")));
        } else if (mode.equals("PRESTITI")){
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Views/Prestiti/PrestitiView.fxml")));
        }
        assert root != null;
        Scene scene = new Scene(root);
        Stage stage = (Stage) item.getParentPopup().getOwnerWindow();
        stage.setScene(scene);
    }
}