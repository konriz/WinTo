package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class GetterController {

    @FXML
    private ListView winesListView;


    @FXML
    void initialize()
    {
        populateListView();
    }

    private ObservableList<String> winesList;

    public void populateListView()
    {
        // TODO make this parse JSON and add wines :)

        winesList = FXCollections.observableArrayList();
        winesList.setAll("Wine1", "Wine2", "Wine3");
        winesListView.setItems(winesList);

    }

}
