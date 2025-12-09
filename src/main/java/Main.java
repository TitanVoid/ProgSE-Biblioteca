import Controllers.Libri.LibriController;
import Models.Biblioteca;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Biblioteca biblioteca = new Biblioteca();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Libri/LibriView.fxml"));
        Parent root = loader.load();
        // Pass instance of biblioteca to controllers
        LibriController controller = loader.getController();
        controller.setBiblioteca(biblioteca);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Biblioteca");
        primaryStage.show();
    }
}