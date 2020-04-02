package org.semesteroppgave.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.semesteroppgave.ComponentSearch;
import org.semesteroppgave.Context;
import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.Main;
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
        txtPriceColumn.setCellValueFactory(new PropertyValueFactory<Component, Double>("price"));
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
            alert.setHeaderText("Du har valgt komponenten: " + tableViewComponents.getSelectionModel().getSelectedItem().getComponent().toLowerCase()
                    + ", versjon: "+ tableViewComponents.getSelectionModel().getSelectedItem().getVersion().toLowerCase());
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

    private void filter(){
        String choiceFilter = cbFilter.getValue();
        String searchWord = txtSearch.getText();
        try {
            newSearch.search(choiceFilter,searchWord,tableViewComponents);
        }catch (InvalidPriceException e){
            Dialogs.showErrorDialog("Feil i søket", e.getMessage(), "Prøv på nytt");
        }
    }

    @FXML
    void editModel(TableColumn.CellEditEvent<Component, String> event) {

        //TODO lage en validator til modell

        try{
            event.getRowValue().setComponent(event.getNewValue());
        }catch (InvalidVersionException e){
            Dialogs.showErrorDialog("Redigeringsfeil","Ugyldig Modell!", e.getMessage());
            tableViewComponents.refresh();
        }
    }

    @FXML
    void editPrice(TableColumn.CellEditEvent<Component, Double> event) {

        //TODO lage en doubleToString converter klasse som kan støtte denne koden (koden her er ferdig)

        /*
        try {
            if(doubleStrConverter.wasSuccessful())
                event.getRowValue().setPrice(event.getNewValue());
        } catch (NumberFormatException e) {
            Dialogs.showErrorDialog("Redigeringsfeil","Ugyldig pris!","Du må skrive inn et positivt tall.");
        } catch (IllegalArgumentException e) {
            Dialogs.showErrorDialog("Redigeringsfeil","Ugyldig pris!", e.getMessage());
        }

        tableView.refresh();

         */

    }

    @FXML
    void editVersion(TableColumn.CellEditEvent<Component, String> event) {

        //TODO lage en validator til versjon

        try{
            event.getRowValue().setVersion(event.getNewValue());
        }catch (InvalidVersionException e){
            Dialogs.showErrorDialog("Redigeringsfeil","Ugyldig versjon!", e.getMessage());
            tableViewComponents.refresh();
        }
    }

    public void loadChoice(){
        componentChoice.removeAll();
        componentChoice.addAll("Motor","Felg","Setetrekk","Ratt","Spoiler","Dekk","Batteri","Tank","Girboks");
        cbCreate.getItems().addAll(componentChoice);
        cbCreate.setValue(componentChoice.get(0));

    }
}
