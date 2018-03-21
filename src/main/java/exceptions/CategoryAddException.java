package exceptions;

import javafx.scene.control.Alert;
import lombok.Getter;

@Getter
public class CategoryAddException extends Exception {

    private Alert alert;

    public CategoryAddException(Entities.Categories category)
    {
        this.alert = new Alert(Alert.AlertType.ERROR, "Can't add category");
    }


}
