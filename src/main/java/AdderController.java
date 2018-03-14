import Entities.Categories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Collections;
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
    // populateBox(Box box)
    // WineryConnector.getCategory(Categories.enum)
    public void populateBoxes()
    {
        HashMap<String, List<String>> categories = WineryConnector.getCategories();

        grapesBox.getItems().clear();
        grapesBox.getItems().addAll(categories.get("grapes"));
        grapesBox.getItems();

        brandBox.getItems().clear();
        brandBox.getItems().addAll(categories.get("brand"));

        countryBox.getItems().clear();
        countryBox.getItems().addAll(categories.get("country"));

        tasteBox.getItems().clear();
        tasteBox.getItems().addAll(categories.get("taste"));

        colourBox.getItems().clear();
        colourBox.getItems().addAll(categories.get("colour"));
    }

    @FXML
    protected void handleNewGrapesButton()
    {
        handleNewButton(Categories.grapes);
    }

    @FXML
    protected void handleNewBrandButton()
    {
        handleNewButton(Categories.brand);
    }

    @FXML
    protected void handleNewCountryButton()
    {
        handleNewButton(Categories.country);
    }

    private void handleNewButton(Categories category)
    {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setContentText(String.format("Add new %s", category.toString()));
        inputDialog.setTitle(String.format("New %s", category.toString()));
        inputDialog.showAndWait();
        String newItem = inputDialog.getEditor().getText();

        try {
            WineryConnector.addItemToCategory(newItem, category);
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.show();
            confirm.setContentText(String.format("%s %s added", category.toString(), newItem));
            this.populateBoxes();


        } catch (CategoryAddException e){
            e.getAlert().show();
        }

    }

}