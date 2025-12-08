package Controllers.Libri;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class LibriController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code
    }

    @FXML
    private void onAddBook(){

    }

    @FXML
    private void onModifyBook(){

    }

    @FXML
    private void onDeleteBook(){

    }

    @FXML
    private void onSearchBook(){
        System.out.println("Test");
    }

    @FXML
    private void onChangeViewMode(ActionEvent event){
        MenuItem item = (MenuItem) event.getSource();
        String mode = (String) item.getUserData();
        System.out.println("mode: " + mode);
    }
}