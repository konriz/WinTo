package exceptions;

import javafx.scene.control.Alert;
import lombok.Getter;

@Getter
public class WineAddException extends Exception{

    private Alert alert;

    public WineAddException()
    {
        this.alert = new Alert(Alert.AlertType.ERROR, "Can't add wine");
    }

}
