package controller;

import Entities.Wine;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.List;

public class DeleterController {

    @FXML
    private TableView winesTableView;


    @FXML
    void initialize()
    {
        populateTableView();
    }

    private ObservableList<String> winesList;



    public void populateTableView()
    {
        // TODO make this parse JSON and add wines :)

        List<Wine> wines = WineryConnector.getWines();
        winesTableView.getItems().setAll(wines);

    }
}
