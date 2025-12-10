package controllers;

import controllers.libri.LibriController;
import javafx.scene.control.Alert;
import models.Biblioteca;

public abstract class BaseController {
    protected Biblioteca biblioteca;
    protected BaseController parentController;

    public void setParentController(BaseController parentController) {
        this.parentController = parentController;
    };

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}