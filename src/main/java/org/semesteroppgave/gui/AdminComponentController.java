package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.semesteroppgave.*;
import org.semesteroppgave.carcomponents.Component;
import org.semesteroppgave.signin.AdminSignin;

import java.io.IOException;

public class AdminComponentController {

    private InputValidation.DoubleStringConverter doubleStrConverter = new InputValidation.DoubleStringConverter();
    private ComponentSearch componentSearch = new ComponentSearch();
    private AdminCreateComponent createComponent = new AdminCreateComponent(componentSearch);

    @FXML
    private TableView<Component> tableViewComponents, tableViewCreate;

    @FXML
    private TableColumn<Component, Double> txtPriceColumn, txtPriceColumnCreate;

    @FXML
    private ComboBox<String> cbFilter, cbCreate;

    @FXML
    private TextField txtSearch, txtVersion, txtPrice;

    @FXML
    private TextArea txtDescription;

    @FXML
    private Label lblAdminID, lblMessageCreate;

    @FXML
    private MenuBar menuBar = new MenuBar();

    public void initialize() {
        tableViewComponents.setItems(Context.getInstance().getRegisterComponent().getComponentsList());
        txtPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        txtPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(doubleStrConverter));
        txtPriceColumnCreate.setCellValueFactory(new PropertyValueFactory<>("price"));
        txtPriceColumnCreate.setCellFactory(TextFieldTableCell.forTableColumn(doubleStrConverter));
        componentSearch.loadFilter(cbFilter);
        createComponent.loadChoice(cbCreate);
        lblAdminID.setText(AdminSignin.getActiveAdminId());
    }

    @FXML
    void btnAdd(ActionEvent event) {
        createComponent.addComponent(lblMessageCreate,tableViewCreate,txtVersion,txtPrice,txtDescription,cbCreate);
    }

    @FXML
    void btnComplete(ActionEvent event) {
        createComponent.completeComponent();
    }

    @FXML
    void btnDeleteComponent(ActionEvent event) {
        createComponent.deleteColumn(tableViewComponents, Context.getInstance().getRegisterComponent().getComponentsList(), true);
    }

    @FXML
    void btnDeleteCreate(ActionEvent event) {
        createComponent.deleteColumn(tableViewCreate, createComponent.getCreateComponentList(), false);
    }

    @FXML
    void btnSignout(ActionEvent event) throws IOException {
        Main.setRoot("adminsignin");
    }

    @FXML
    void editComponent(TableColumn.CellEditEvent<Component, String> event) {
        createComponent.editComponentColumn(event, tableViewComponents);
    }

    @FXML
    void editPrice(TableColumn.CellEditEvent<Component, Double> event) {
        createComponent.editPriceColumn(event, doubleStrConverter, tableViewComponents);
    }

    @FXML
    void editPriceCreate(TableColumn.CellEditEvent<Component, Double> event) {
        createComponent.editPriceColumn(event, doubleStrConverter, tableViewCreate);
    }

    @FXML
    void editVersion(TableColumn.CellEditEvent<Component, String> event) {
        createComponent.editVersionColumn(event, tableViewComponents);
    }

    @FXML
    void editVersionCreate(TableColumn.CellEditEvent<Component, String> event) {
        createComponent.editVersionColumn(event, tableViewCreate);
    }

    @FXML
    void filterChoiceChanged(ActionEvent event) {
        componentSearch.filter(txtSearch,tableViewComponents,cbFilter);
    }

    @FXML
    void onKeyTypedSearch(KeyEvent event) {
        componentSearch.filter(txtSearch,tableViewComponents,cbFilter);
    }

    @FXML
    void tabComponent(Event event) {
        menuBar.setVisible(true);
    }

    @FXML
    void tabCreate(Event event) {
        menuBar.setVisible(false);
    }

    @FXML
    void openFileMenuClicked(ActionEvent event) {
        FileHandler.openFileJobj();
    }

    @FXML
    void saveFileMenuClicked(ActionEvent event) {
        FileHandler.saveFileJobj();
    }
}
