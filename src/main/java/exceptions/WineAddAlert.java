package exceptions;

import javafx.scene.control.Alert;

public class WineAddAlert extends Alert{

    public WineAddAlert()
    {
        super(AlertType.ERROR);
        this.setContentText("Can't add wine to database. Check logs.");
    }
}
