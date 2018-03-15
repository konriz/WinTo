import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WinesAdderApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_view.fxml"));
        Parent root = loader.load();

        MainController mainController = loader.getController();

        Scene scene = new Scene(root);
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();

    }
}
