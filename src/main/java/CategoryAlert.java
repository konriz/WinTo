import javafx.scene.control.Alert;

public class CategoryAlert extends Alert {

    public CategoryAlert(Entities.Categories category)
    {
        super(AlertType.ERROR);
        this.setContentText(String.format("Can't add this %s", category.toString()));
    }
}
