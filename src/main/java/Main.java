import controllers.libri.LibriController;
import models.Biblioteca;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Biblioteca biblioteca = new Biblioteca();
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
}