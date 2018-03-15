package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab adderTab;

    @FXML
    private Tab getterTab;

    @FXML
    private Tab deleterTab;

    @FXML
    private AdderController adderController;

    @FXML
    private GetterController getterController;

    @FXML
    private DeleterController deleterController;


}
