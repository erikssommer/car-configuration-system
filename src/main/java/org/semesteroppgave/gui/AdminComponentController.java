package org.semesteroppgave.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import org.semesteroppgave.*;
import org.semesteroppgave.carcomponents.Component;
import org.semesteroppgave.personsUserAdmin.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminComponentController implements Initializable {

    private ObservableList<String> componentChoice = FXCollections.observableArrayList();
    private InputValidation.DoubleStringConverter doubleStrConverter = new InputValidation.DoubleStringConverter();
    private AdminCreateComponent createComponent = new AdminCreateComponent();

    @FXML
    private Label lblAdminID;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<Component> tableViewComponents;

    @FXML
    private TableColumn<Component, Double> txtPriceColumn;

    @FXML
    private ComboBox<String> cbFilter;

    @FXML
    private ComboBox<String> cbCreate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //FileHandler.openFileJobj();
        tableViewComponents.setItems(Context.getInstance().getRegisterComponent().getComponentsList());
        txtPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        txtPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(doubleStrConverter));
        lblAdminID.setText(Admin.activeAdminId);
        ComponentSearch.getInstance().loadFilter(cbFilter);
        loadChoice();
    }

    @FXML
    void btnCreate(ActionEvent event) throws IOException {
        AdminCreateComponent.setNewComponent(cbCreate.getValue());
        Main.setRoot("admincreate");
    }

    @FXML
    void btnDeleteComponent(ActionEvent event) {
        createComponent.deleteColumn(tableViewComponents, Context.getInstance().getRegisterComponent().getComponentsList(), true);
    }


    @FXML
    void btnExport(ActionEvent event) {
        FileHandler.saveFileJobj();
    }

    @FXML
    void btnSignout(ActionEvent event) throws IOException {
        Main.setRoot("adminsignin");
    }

    @FXML
    void onKeyTypedSearch(KeyEvent event) {
        ComponentSearch.getInstance().filter(txtSearch,tableViewComponents,cbFilter);
    }

    @FXML
    private void filterChoiceChanged() {
        ComponentSearch.getInstance().filter(txtSearch,tableViewComponents,cbFilter);
    }

    @FXML
    void editComponent(TableColumn.CellEditEvent<Component, String> event) {
        createComponent.editComponentColumn(event, tableViewComponents);
    }

    @FXML
    void editVersion(TableColumn.CellEditEvent<Component, String> event) {
        createComponent.editVersionColumn(event, tableViewComponents);
    }

    @FXML
    void editPrice(TableColumn.CellEditEvent<Component, Double> event) {
        createComponent.editPriceColumn(event, doubleStrConverter, tableViewComponents);
    }

    public void loadChoice(){
        componentChoice.removeAll();
        componentChoice.addAll("Motor","Felg","Setetrekk","Spoiler","Dekk","Batteri","Tank","Girboks");
        cbCreate.getItems().addAll(componentChoice);
        cbCreate.setValue(componentChoice.get(0));

    }
}
