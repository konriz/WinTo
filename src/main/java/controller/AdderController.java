package controller;

import Entities.Categories;
import Entities.Wine;
import exceptions.CategoryAddException;
import exceptions.WineAddException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    private CheckBox drinkedBox;

    @FXML
    private Label resultLabel;

    @FXML
    void initialize()
    {
        assert grapesBox != null : "GrapesBox not injected!";
        assert tasteBox != null : "tasteBox not injected!";
        assert colourBox != null : "colourBox not injected!";
        assert countryBox != null : "countryBox not injected!";
        assert brandBox != null : "brandBox not injected!";

        populateBoxes();

    }

    @FXML
    protected void handleCreateWine()
    {
        Wine wine = new Wine(nameField.getText(), brandBox.getValue());
        wine.setGrapes(grapesBox.getValue());
        wine.setColour(colourBox.getValue());
        wine.setTaste(tasteBox.getValue());
        wine.setCountry(countryBox.getValue());
        wine.setYear(yearField.getText());
        wine.setAlcohol(alcoholField.getText());
        wine.setVolume(volumeField.getText());
        wine.setDrinked(drinkedBox.isSelected());

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to add wine:\n" + wine.toString());
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.get() == ButtonType.OK)
        {
            try {
                WineryConnector.addWine(wine);
            } catch (WineAddException e)
            {
                e.getAlert().show();
            }
        }


    }

    public void populateBox(ComboBox<String> box, Categories type)
    {
        box.getItems().setAll(WineryConnector.getCategory(type));
    }

    public void populateBoxes()
    {
        HashMap<String, List<String>> categories = WineryConnector.getCategories();

        grapesBox.getItems().setAll(categories.get("grapes"));

        brandBox.getItems().setAll(categories.get("brand"));

        countryBox.getItems().setAll(categories.get("country"));

        tasteBox.getItems().setAll(categories.get("taste"));

        colourBox.getItems().setAll(categories.get("colour"));
    }

    @FXML
    protected void handleNewGrapesButton()
    {
        handleNewButton(Categories.grapes);
        populateBox(grapesBox, Categories.grapes);
    }

    @FXML
    protected void handleNewBrandButton()
    {
        handleNewButton(Categories.brand);
        populateBox(brandBox, Categories.brand);;
    }

    @FXML
    protected void handleNewCountryButton()
    {
        handleNewButton(Categories.country);
        populateBox(countryBox, Categories.country);
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

        } catch (CategoryAddException e){
            e.getAlert().show();
        }

    }

}