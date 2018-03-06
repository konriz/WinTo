import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdderController {

    @FXML
    private TextField nameField, yearField, alcoholField, volumeField;

    @FXML
    private TextField grapesField;

    @FXML
    private Button newGrapesButton, newBrandButton, newCountryButton, createWine;

    @FXML
    private TextField brandField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField tasteField;

    @FXML
    private TextField colourField;

    @FXML
    private Label resultLabel;

    @FXML
    protected void handleCreateWine()
    {
        Wine.WineBuilder createdWine = new Wine.WineBuilder(nameField.getText(), brandField.getText());

        WineryConnector.getCategories();

        resultLabel.setText(createdWine.build().toString());
    }

}