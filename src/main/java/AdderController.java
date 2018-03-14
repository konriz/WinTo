import Entities.Categories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    // TODO divide this to single categories to shorten population duration when refreshing
    // populateBox(Categories.enum)
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

    @FXML
    protected void handleNewBrandButton()
    {
        TextInputDialog newBrandDialog = new TextInputDialog();
        newBrandDialog.setContentText("Add new brand");
        newBrandDialog.setTitle("New brand");
        newBrandDialog.showAndWait();
        String newBrand = newBrandDialog.getEditor().getText();

        if (newBrand != "")
        {
            // TODO make this throw exception!
            WineryConnector.addItemToCategory(newBrandDialog.getEditor().getText(), Categories.brand);
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.show();
            confirm.setContentText(String.format("Brand %s added", newBrand));
            this.populateBoxes();
        }
        else
        {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Can't add such brand!");
            error.show();
        }


    }

    @FXML
    protected void handleNewCountryButton()
    {
        WineryConnector.addItemToCategory("Unknown", Categories.country);
    }

}