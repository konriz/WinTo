package controller;

import Entities.Wine;
import exceptions.WineDeleteException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class MainController {

    @FXML
    private ButtonBar topButtonBar;

    @FXML
    private Button addButton;

    @FXML
    private TableView<Wine> winesTableView;

    @FXML
    void initialize()
    {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/adder_view.fxml"));
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
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to delete wine:\n" + wine.toString());
        confirmAlert.setTitle("Delete wine");
        Optional<ButtonType> result = confirmAlert.showAndWait();

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
