package org.semesteroppgave.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.services.UserCreateProduct;
import org.semesteroppgave.models.data.components.Component;
import org.semesteroppgave.models.data.customizations.Custom;
import org.semesteroppgave.models.exceptions.DuplicateException;
import org.semesteroppgave.models.exceptions.EmptyComponentException;
import org.semesteroppgave.models.signin.user.UserSignIn;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.IOException;

public class UserBuildProductController implements ApplicationController{

    private UserCreateProduct newProduct;

    @FXML
    private ComboBox<String> cbModel;

    @FXML
    private CheckBox cbAutopilot, cbTowbar, cbSunroof, cbGps;

    @FXML
    private TableView<String> tableViewComponent;

    @FXML
    private TableView<Component> tableViewVersion;

    @FXML
    private TableColumn<Component, Double> txtPriceColumn;

    @FXML
    private TableColumn<String, String> columnComponent;

    @FXML
    private GridPane gridPaneCustom;

    @FXML
    private TextField txtTotalPrice;

    @FXML
    private Label lblMessage, lblUsername;

    @Override
    public void initialize() {
        newProduct = new UserCreateProduct(tableViewComponent, tableViewVersion, cbModel, lblMessage, txtTotalPrice);
        newProduct.loadChoice();
        txtPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnComponent.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        cbAutopilot.setVisible(false);
        gridPaneCustom.setDisable(true);
        lblUsername.setText(UserSignIn.getActiveUsername());
    }

    @FXML
    private void btnShowConfig() throws IOException {
        Main.setRoot("configuredproduct");
    }

    @FXML
    private void btnSignout() throws IOException {
        Main.setRoot("usersignin");
    }

    @FXML
    private void choiseMade() {

        cbModel.valueProperty().addListener((ov, previous, active) -> {
            newProduct.createNewProduct(active);
            resetChoiceBox(active);
        });
    }

    private void resetChoiceBox(String model) {

        cbAutopilot.setVisible(model.equals("Elektrisk"));

        gridPaneCustom.setDisable(false);
        tableViewComponent.setDisable(false);
        tableViewVersion.setDisable(false);
        cbAutopilot.setSelected(false);
        cbGps.setSelected(false);
        cbSunroof.setSelected(false);
        cbTowbar.setSelected(false);
        newProduct.updateLivePrice();
    }

    @FXML
    private void btnDone() {
        try {
            newProduct.finishedProduct();
        } catch (EmptyComponentException | DuplicateException e) {
            Dialogs.showErrorDialog("Oups", "Feil i oppretting av komponenter", e.getMessage());
        }
    }

    //Valg av tilpasninger med checkboxer
    @FXML
    public void cbGps() {
        newProduct.customization(cbGps, Custom.GPS);
    }

    @FXML
    public void cbSunroof() {
        newProduct.customization(cbSunroof, Custom.SUNROOF);
    }

    @FXML
    public void cbTowbar() {
        newProduct.customization(cbTowbar, Custom.TOWBAR);
    }

    @FXML
    public void cbAutopilot() {
        newProduct.customization(cbAutopilot, Custom.AUTOPILOT);
    }
}
