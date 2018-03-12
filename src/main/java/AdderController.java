import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.List;

public class AdderController {

    @FXML
    private TextField nameField, yearField, alcoholField, volumeField;

    @FXML
    private ComboBox<String> grapesBox;

    @FXML
    private Button newGrapesButton, newBrandButton, newCountryButton, createWine;

    @FXML
    private ComboBox<String> brandBox;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private ComboBox<String> tasteBox;

    @FXML
    private ComboBox<String> colourBox;

    @FXML
    private Label resultLabel;

    @FXML
    protected void handleCreateWine()
    {

    }

    public void populateBoxes()
    {
        HashMap<String, List<String>> categories = WineryConnector.getCategories();

        grapesBox.getItems().clear();
        grapesBox.getItems().addAll(categories.get("grapes"));

        brandBox.getItems().clear();
        brandBox.getItems().addAll(categories.get("brand"));

        countryBox.getItems().clear();
        countryBox.getItems().addAll(categories.get("country"));

        tasteBox.getItems().clear();
        tasteBox.getItems().addAll(categories.get("taste"));

        colourBox.getItems().clear();
        colourBox.getItems().addAll(categories.get("colour"));

    }

    // TODO show add dialog

//    @FXML
//    protected void handleNewBrandButton()
//    {
//        resultLabel.setText(WineryConnector.addBrand(brandField.getText()));
//    }

}