package org.semesteroppgave.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.semesteroppgave.Main;
import org.semesteroppgave.UserCreateCar;
import org.semesteroppgave.carcomponents.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserBuildCarController implements Initializable {

    private ObservableList<String> modelChoice = FXCollections.observableArrayList();
    private UserCreateCar newCar;

    @FXML
    private ComboBox<String> cbModel;

    @FXML
    private CheckBox cbAutopilot, cbTowbar, cbSunroof, cbGps;

    @FXML
    private TableView<Component> tableViewComponent;

    @FXML
    private TableView<Component> tableViewVersion;

    @FXML
    private TableColumn<Component, Double> txtPriceColumn;

    @FXML
    private GridPane gridPaneCustom;

    @FXML
    private TextField txtTotalPrice;

    @FXML
    private Label lblMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadChoice();
        newCar = new UserCreateCar(tableViewComponent, tableViewVersion, cbModel, lblMessage, txtTotalPrice);
        txtPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        cbAutopilot.setVisible(false);
        gridPaneCustom.setDisable(true);
    }

    @FXML
    void btnShowConfig(ActionEvent event) throws IOException {
        Main.setRoot("configuredcars");
    }

    @FXML
    void btnSignout(ActionEvent event) throws IOException {
        Main.setRoot("usersignin");
    }

    @FXML
    void choiseMade(Event event) {
        //TODO dette kan nok skrives på en bedre måte, men det funker
        //Teller som forsikrer at changed metoden bare kjøres én gang for hver event
        final int[] counter = {0};
        cbModel.valueProperty().addListener((ov, previous, active) -> {
            if (counter[0] == 0){
                switch (active){
                    case "Elektrisk": newCar.createNewCar("Elektrisk", "universial");
                        resetChoiceBox(true);
                        counter[0]++;
                        break;
                    case "Diesel": newCar.createNewCar("Diesel", "universial");
                        resetChoiceBox(false);
                        counter[0]++;
                        break;
                    case "Hybrid": newCar.createNewCar("Hybrid", "universial");
                        resetChoiceBox(false);
                        counter[0]++;
                        break;
                }
            }
        });
    }

    public void resetChoiceBox(boolean state){

        if (state){
            cbAutopilot.setVisible(true);
        }else {
            cbAutopilot.setVisible(false);
        }

        gridPaneCustom.setDisable(false);
        tableViewComponent.setDisable(false);
        tableViewVersion.setDisable(false);
        cbAutopilot.setSelected(false);
        cbGps.setSelected(false);
        cbSunroof.setSelected(false);
        cbTowbar.setSelected(false);
        newCar.customization(cbAutopilot,cbTowbar,cbSunroof,cbGps);
        newCar.updateLivePrice();
    }


    @FXML
    void cbClicked(ActionEvent event) {

        newCar.customization(cbAutopilot,cbTowbar,cbSunroof,cbGps);

    }

    @FXML
    void btnDone(ActionEvent event) {
        newCar.finishedCar();
    }

    public void loadChoice(){
        modelChoice.removeAll();
        modelChoice.addAll("Elektrisk", "Diesel", "Hybrid");
        cbModel.getItems().addAll(modelChoice);
        cbModel.setPromptText("Velg modell");
    }
}
