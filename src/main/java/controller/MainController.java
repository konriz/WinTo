package controller;

import Entities.Wine;
import exceptions.WineDeleteException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;


public class MainController implements Initializable {

    @FXML
    private ButtonBar topButtonBar;

    @FXML
    private Button addButton;

    @FXML
    private TableView<Wine> winesTableView;

    private ResourceBundle resources;

    @FXML
    public void initialize(URL url, ResourceBundle resources)
    {
        this.resources = resources;
        populateTableView();
    }

    public void populateTableView()
    {
        List<Wine> wines = WineryConnector.getWines();
        Collections.sort(wines);
        winesTableView.getItems().setAll(wines);
    }

    public void handleAddButton()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/adder_view.fxml"),
                    ResourceBundle.getBundle("winto", new Locale("pl", "PL")));
            Parent root = loader.load();

            AdderController adderController = loader.getController();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
            populateTableView();
        } catch (Exception e)
        {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Add dialog failed!").showAndWait();
        }
    }

    public void handleDeleteButton()
    {
        Wine wine = winesTableView.getFocusModel().getFocusedItem();
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,  resources.getString("deleteConfirm") +"\n" + wine.toString());
        confirm.setHeaderText(resources.getString("confirmation"));
        confirm.setTitle("Delete wine");
        Optional<ButtonType> result = confirm.showAndWait();

        if(result.get() == ButtonType.OK)
        {
            try
            {
                WineryConnector.deleteWine(wine);
                populateTableView();
            } catch (WineDeleteException e)
            {
                e.getAlert().show();
            }
        }
    }
}
