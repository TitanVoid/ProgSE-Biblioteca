package controllers;

import controllers.libri.LibriController;
import controllers.prestiti.PrestitiController;
import controllers.utenti.UtentiController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.Biblioteca;


import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MenuController extends BaseController implements Initializable {
    private Biblioteca biblioteca;
    private final Map<String, Stage> windows = new HashMap<>();

    @FXML
    private Button libriBtn;

    @FXML
    private Button utentiBtn;

    @FXML
    private Button prestitiBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization code

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

            if (controller instanceof PrestitiController){
                PrestitiController cont = (PrestitiController) controller;
                cont.refreshLoans();
            }
            if (controller instanceof LibriController){
                LibriController cont = (LibriController) controller;
                cont.refreshBooks();
            }

            if (controller instanceof UtentiController){
                UtentiController cont = (UtentiController) controller;
                cont.refreshUsers();
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
    public void onLibri(){
        showNewWindow("/views/libri/LibriView.fxml", "Libro");
    }

    @FXML
    public void onUtenti(){
        showNewWindow("/views/utenti/UtentiView.fxml", "Utenti");
    }

    @FXML
    public void onPrestiti(){
        showNewWindow("/views/prestiti/PrestitiView.fxml", "Prestiti");
    }

}
