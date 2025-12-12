import controllers.libri.LibriController;
import javafx.scene.control.Alert;
import models.Biblioteca;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Biblioteca biblioteca;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            biblioteca = Biblioteca.leggiBibliotecaObj("biblioteca.obj");
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.showAndWait();
            biblioteca = new Biblioteca();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/libri/LibriView.fxml"));
        Parent root = loader.load();
        // Pass instance of biblioteca to controllers
        LibriController controller = loader.getController();
        controller.setBiblioteca(biblioteca);
        controller.refreshBooks();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Biblioteca");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        try {
            biblioteca.salvaBibliotecaObj("biblioteca.obj");
            System.out.println("Biblioteca salvata");
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            alert.showAndWait();
        }
        super.stop();
    }
}