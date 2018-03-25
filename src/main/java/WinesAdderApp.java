import controller.MainController;
import controller.WineryConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class WinesAdderApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        WineryConnector.setProperties();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_view.fxml"),
                ResourceBundle.getBundle("winto", new Locale("pl", "PL")));
        Parent root = loader.load();

        MainController mainController = loader.getController();

        Scene scene = new Scene(root);
        stage.setTitle("WinTo");
        stage.setScene(scene);
        stage.show();

    }
}
