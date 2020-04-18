package org.semesteroppgave.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.data.UserCreateCar;
import org.semesteroppgave.models.data.carcomponents.Component;
import org.semesteroppgave.models.exceptions.DuplicateException;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.IOException;

public class UserBuildCarController {

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

    public void initialize() {
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

        cbModel.valueProperty().addListener((ov, previous, active) -> {
            switch (active){
                case "Elektrisk": newCar.createNewCar("Elektrisk", "universial");
                resetChoiceBox(true);
                break;
                case "Diesel": newCar.createNewCar("Diesel", "universial");
                resetChoiceBox(false);
                break;
                case "Hybrid": newCar.createNewCar("Hybrid", "universial");
                resetChoiceBox(false);
                break;
            }
        });
    }

    public void resetChoiceBox(boolean state){

        cbAutopilot.setVisible(state);

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
        try {
            newCar.finishedCar();
        }catch (NullPointerException | DuplicateException e){
            //TODO Endre til en egendefinert exeption
            //Fnger her nullpoinerexception fordi det kastes fra component klassen hvis komponent ikke er valgt
            Dialogs.showErrorDialog("Oups", "Feil i oppretting av komponenter", e.getMessage());
        }
    }

    public void loadChoice(){
        modelChoice.removeAll();
        modelChoice.addAll("Elektrisk", "Diesel", "Hybrid");
        cbModel.getItems().addAll(modelChoice);
        cbModel.setPromptText("Velg modell");
    }
}
