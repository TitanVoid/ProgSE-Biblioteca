package controllers;

import controllers.libri.LibriController;
import controllers.prestiti.PrestitiController;
import controllers.utenti.UtentiController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.*;

public class MenuController extends BaseController {

    @FXML
    private Button libriBtn;

    @FXML
    private Button utentiBtn;

    @FXML
    private Button prestitiBtn;

    private void showNewWindow(String viewName, String title) {
        try{
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

            Scene scene = libriBtn.getScene();
            scene.setRoot(root);
        } catch (IOException | NullPointerException ex) {
            showErrorAlert("Error", "Could Not Find FXML at " + viewName);
        }
    }

    @FXML
    public void onLibri(){
        showNewWindow("/views/libri/LibriView.fxml", "Libri");
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
