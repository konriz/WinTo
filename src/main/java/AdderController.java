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
        grapesBox.getItems().clear();
        grapesBox.getItems().addAll(WineryConnector.getCategories().get("grapes"));

        brandBox.getItems().clear();
        brandBox.getItems().addAll(WineryConnector.getCategories().get("brand"));

        countryBox.getItems().clear();
        countryBox.getItems().addAll(WineryConnector.getCategories().get("country"));

        tasteBox.getItems().clear();
        tasteBox.getItems().addAll(WineryConnector.getCategories().get("taste"));

        colourBox.getItems().clear();
        colourBox.getItems().addAll(WineryConnector.getCategories().get("colour"));

    }

}