package org.semesteroppgave.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.semesteroppgave.Context;
import org.semesteroppgave.UserCreateCar;
import org.semesteroppgave.carcomponents.Component;
import org.semesteroppgave.Main;
import org.semesteroppgave.carcomponents.Motor;
import org.semesteroppgave.carmodel.Car;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserBuildCarController implements Initializable {

    private ObservableList<String> modelChoice = FXCollections.observableArrayList();
    UserCreateCar newCar;

    @FXML
    private ComboBox<String> cbModel;

    @FXML
    private CheckBox cbAutopilot;

    @FXML
    private TableView<Component> tableViewComponent;

    @FXML
    private TableView<Component> tableViewVersion;

    @FXML
    private TableColumn<Component, Double> txtPriceColumn;

    @FXML
    private TextField txtTotalPrice;

    @FXML
    private Label lblMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadChoice();
        newCar = new UserCreateCar(tableViewComponent, tableViewVersion, cbModel, lblMessage, txtTotalPrice);
        txtPriceColumn.setCellValueFactory(new PropertyValueFactory<Component, Double>("price"));
        cbAutopilot.setVisible(false);
    }

    @FXML
    void btnShowConfigs(ActionEvent event) throws IOException {
        Main.setRoot("configuredcars");
    }

    @FXML
    void btnShowMyConfig(ActionEvent event) throws IOException {
        Main.setRoot("configuredcars");
    }

    @FXML
    void btnSignout(ActionEvent event) throws IOException {
        Main.setRoot("usersignin");
    }

    @FXML
    void choiseMade(Event event) {
        cbModel.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String previous, String active) {
                switch (active){
                    case "Elektrisk": newCar.createNewCar("Electric", "universial");
                        cbAutopilot.setVisible(true);
                        break;
                    case "Diesel": newCar.createNewCar("Diesel", "universial");
                        break;
                    case "Hybrid": newCar.createNewCar("Hybrid", "universial");
                        break;
                }
            }
        });
    }


    @FXML
    void cbClicked(ActionEvent event) {
        //Samler alle checkboxer i ￿￿￿￿én metode
        String value = ((CheckBox)event.getSource()).getText();
        boolean state = ((CheckBox)event.getSource()).isSelected();

        if (state){
            lblMessage.setText(value + " er true");
        }else {
            lblMessage.setText(value + " er false");
        }
    }

    @FXML
    void btnDone(ActionEvent event) {
        Car car = newCar.finishedCar();
        Context.getInstance().getRegisterProduct().setCarList(car);
    }



    public void loadChoice(){
        modelChoice.removeAll();
        modelChoice.addAll("Elektrisk", "Diesel", "Hybrid");
        cbModel.getItems().addAll(modelChoice);
        cbModel.setPromptText("Velg modell");


    }
}
