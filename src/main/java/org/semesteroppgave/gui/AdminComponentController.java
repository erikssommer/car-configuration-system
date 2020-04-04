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
import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.exceptions.InvalidComponentException;
import org.semesteroppgave.exceptions.InvalidPriceException;
import org.semesteroppgave.exceptions.InvalidVersionException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminComponentController implements Initializable {

    private ObservableList<String> componentChoice = FXCollections.observableArrayList();
    private ObservableList<String> componentFilter = FXCollections.observableArrayList();
    private ComponentSearch newSearch = new ComponentSearch();
    private InputValidation.DoubleStringConverter doubleStrConverter
            = new InputValidation.DoubleStringConverter();

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
        tableViewComponents.setItems(Context.getInstance().getRegisterComponent().getComponentsList());
        txtPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        txtPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(doubleStrConverter));
        newSearch.loadFilter(cbFilter);
        loadChoice();
    }

    @FXML
    void btnCreate(ActionEvent event) throws IOException {
        Context.getInstance().getRegisterComponent().setNewComponent(cbCreate.getValue());
        Main.setRoot("admincreate");
    }

    @FXML
    void btnDeleteComponent(ActionEvent event) {
        if (tableViewComponents.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bekreft");
            alert.setHeaderText("Du har valgt komponenten: " + tableViewComponents.getSelectionModel().getSelectedItem().getComponent()
                    + ", versjon: "+ tableViewComponents.getSelectionModel().getSelectedItem().getVersion());
            alert.setContentText("Ønsker du virkerlig å slette denne komponenten?");
            alert.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK){
                    Context.getInstance().getRegisterComponent().getComponentsList().remove(tableViewComponents.getSelectionModel().getSelectedItem());
                }
            });
        }else {
            Dialogs.showErrorDialog("Feil", "Du har ikke valgt en komponent", "Velg en komponent og prøv på nytt");
        }
    }


    @FXML
    void btnExport(ActionEvent event) {

    }

    @FXML
    void btnSignout(ActionEvent event) throws IOException {
        Main.setRoot("adminsignin");
    }

    @FXML
    void onKeyTypedSearch(KeyEvent event) {
        filter();
    }

    @FXML
    private void filterChoiceChanged() {
        filter();
    }

    private void filter(){
        if(txtSearch.getText().isEmpty()) {
            tableViewComponents.setItems(Context.getInstance().getRegisterComponent().getComponentsList());
        }else {
            String choiceFilter = cbFilter.getValue();
            String searchWord = txtSearch.getText();
            try {
                newSearch.search(choiceFilter,searchWord,tableViewComponents);
            }catch (InvalidPriceException e){
                Dialogs.showErrorDialog("Feil i søket", e.getMessage(), "Prøv på nytt");
            }
        }
    }

    @FXML
    void editComponent(TableColumn.CellEditEvent<Component, String> event) {
        try{
            event.getRowValue().setComponent(InputValidation.testValidComponent(event.getNewValue()));
            ComponentConverter.convert(event.getTableView().getSelectionModel().getSelectedItem());
        }catch (InvalidComponentException e){
            Dialogs.showErrorDialog("Redigeringsfeil","Ugyldig komponent!", e.getMessage());
            tableViewComponents.refresh();
        }
    }

    @FXML
    void editVersion(TableColumn.CellEditEvent<Component, String> event) {

        try{
            event.getRowValue().setVersion(InputValidation.testValidVersion(event.getNewValue()));
        }catch (InvalidVersionException e){
            Dialogs.showErrorDialog("Redigeringsfeil","Ugyldig versjon!", e.getMessage());
            tableViewComponents.refresh();
        }
    }

    @FXML
    void editPrice(TableColumn.CellEditEvent<Component, Double> event) {

        try {
            if(doubleStrConverter.wasSuccessful()){
                event.getRowValue().setPrice(event.getNewValue());
            }
        } catch (NumberFormatException e) {
            Dialogs.showErrorDialog("Feil,","Feil i pris", "Du må skrive inn et positivt tall");
        } catch (IllegalArgumentException e) {
            Dialogs.showErrorDialog("Feil","Ugyldig pris: ", e.getMessage());
        }
        tableViewComponents.refresh();

    }

    public void loadChoice(){
        componentChoice.removeAll();
        componentChoice.addAll("Motor","Felg","Setetrekk","Ratt","Spoiler","Dekk","Batteri","Tank","Girboks");
        cbCreate.getItems().addAll(componentChoice);
        cbCreate.setValue(componentChoice.get(0));

    }
}
