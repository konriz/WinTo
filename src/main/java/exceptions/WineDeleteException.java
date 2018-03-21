package exceptions;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import lombok.Getter;

@Getter
public class WineDeleteException extends Exception{

    Alert alert;

    public WineDeleteException()
    {
        this.alert = new Alert(Alert.AlertType.ERROR, "Can't delete wine");
    }
}
