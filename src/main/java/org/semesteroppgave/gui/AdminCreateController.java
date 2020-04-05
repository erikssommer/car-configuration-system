package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.semesteroppgave.AdminCreateComponent;
import org.semesteroppgave.Context;
import org.semesteroppgave.InputValidation;
import org.semesteroppgave.Main;
import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.exceptions.InvalidVersionException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminCreateController implements Initializable {

    private InputValidation.DoubleStringConverter doubleStrConverter = new InputValidation.DoubleStringConverter();

    @FXML
    private Label lblComponent, lblMessage;

    @FXML
    private TextField txtVersion, txtPrice;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TableView<Component> tableViewAddedConfig;

    @FXML
    private TableColumn<Component, String> txtVersionColumn;

    @FXML
    private TableColumn<Component, Double> txtPriceColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        txtPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(doubleStrConverter));
        lblComponent.setText(Context.getInstance().getRegisterComponent().getNewComponent());
    }

    @FXML
    void btnAdd(ActionEvent event) {
        AdminCreateComponent.addComponent(lblMessage,tableViewAddedConfig,txtVersion,txtPrice,txtDescription);
    }

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Context.getInstance().getRegisterComponent().getCreateComponentList().clear();
        Main.setRoot("admincomponent");
    }

    @FXML
    void btnComplete(ActionEvent event) {
        AdminCreateComponent.completeComponent();
    }

    @FXML
    void btnDelete(ActionEvent event) {
        AdminCreateComponent.deleteColumn(tableViewAddedConfig, Context.getInstance().getRegisterComponent().getCreateComponentList());
    }

    @FXML
    void editPrice(TableColumn.CellEditEvent<Component, Double> event) {
        AdminCreateComponent.editPriceColumn(event, doubleStrConverter, tableViewAddedConfig);
    }

    @FXML
    void editVersion(TableColumn.CellEditEvent<Component, String> event) {
        AdminCreateComponent.editVersionColumn(event, tableViewAddedConfig);
    }
}
