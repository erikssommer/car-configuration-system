package org.semesteroppgave.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import org.semesteroppgave.Context;
import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminComponentController implements Initializable {

    private ObservableList<String> componentChoise = FXCollections.observableArrayList();

    @FXML
    private Label lblAdminID;

    @FXML
    private TableView<Component> tableViewComponents;

    @FXML
    private ChoiceBox<String> cbFilter;

    @FXML
    private ChoiceBox<String> cbCreate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadChoice();
    }

    @FXML
    void btnCreate(ActionEvent event) throws IOException {

        switch (cbCreate.getValue()){
            case "Motor": Context.getInstance().getRegisterComponent().setNewComponent("Motor");
            break;
            case "Felg": Context.getInstance().getRegisterComponent().setNewComponent("Felg");
            break;
            case "Setetrekk": Context.getInstance().getRegisterComponent().setNewComponent("Setetrekk");
            break;
            case "Ratt": Context.getInstance().getRegisterComponent().setNewComponent("Ratt");
            break;
            case "Spoiler": Context.getInstance().getRegisterComponent().setNewComponent("Spoiler");
            break;
            case  "Dekk": Context.getInstance().getRegisterComponent().setNewComponent("Dekk");
            break;
            case "Batteri": Context.getInstance().getRegisterComponent().setNewComponent("Batteri");
            break;
            case "Tank": Context.getInstance().getRegisterComponent().setNewComponent("Tank");
            break;
            case "Girboks": Context.getInstance().getRegisterComponent().setNewComponent("Girboks");
            break;
        }
        Main.setRoot("admincreate");
    }


    @FXML
    void btnExport(ActionEvent event) {

    }

    @FXML
    void btnSignout(ActionEvent event) throws IOException {
        Main.setRoot("adminsignin");
    }

    @FXML
    void txtSearch(KeyEvent event) {

    }

    @FXML
    void editModel(TableColumn.CellEditEvent<Component, String> event) {

    }

    @FXML
    void editPrice(TableColumn.CellEditEvent<Component, Double> event) {

    }

    @FXML
    void editVersion(TableColumn.CellEditEvent<Component, String> event) {

    }

    public void loadChoice(){
        componentChoise.removeAll();
        componentChoise.addAll("Motor","Felg","Setetrekk","Ratt","Spoiler","Dekk","Batteri","Tank","Girboks");
        cbCreate.getItems().addAll(componentChoise);
        cbCreate.setValue("Motor");

    }
}
