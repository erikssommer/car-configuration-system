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
import org.semesteroppgave.Context;
import org.semesteroppgave.carcomponents.Component;
import org.semesteroppgave.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserBuildCarController implements Initializable {

    private ObservableList<String> modelChoice = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cbModel;

    @FXML
    private CheckBox chAutopilot;

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
        txtPriceColumn.setCellValueFactory(new PropertyValueFactory<Component, Double>("price"));
        loadChoice();
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
                    case "Elektrisk": createNewCar("Electric", "universial");
                        break;
                    case "Diesel": createNewCar("Diesel", "universial");
                        break;
                    case "Hybrid": createNewCar("Hybrid", "universial");
                        break;
                }
            }
        });
    }

    public void createNewCar(String model1, String model2){
        Context.getInstance().getRegisterComponent().clearModelComponentsList();
        for (Component model : Context.getInstance().getRegisterComponent().getComponentsList()){
            for (String componentModel : model.getModel()){
                if (componentModel.equals(model1) || componentModel.equals(model2)){
                    if (!redundance(model.getComponent())){
                        Context.getInstance().getRegisterComponent().setModelComponentsList(model);
                    }
                }
            }
        }
        tableViewComponent.setItems(Context.getInstance().getRegisterComponent().getModelComponentsList());

        tableViewComponent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            showComponents(tableViewComponent.getSelectionModel().getSelectedItem().getComponent());
        });
    }

    public boolean redundance(String componentModel){
        boolean redundance = false;
        for (Component component : Context.getInstance().getRegisterComponent().getModelComponentsList()){
            redundance = componentModel.equals(component.getComponent());
        }
        return redundance;
    }

    public void showComponents(String selectedComponent){
        Context.getInstance().getRegisterComponent().clearChooseComponentList();
        for (Component component : Context.getInstance().getRegisterComponent().getComponentsList()){
            if (component.getComponent().equals(selectedComponent)){
                Context.getInstance().getRegisterComponent().setChooseComponentList(component);
            }
        }
        tableViewVersion.setItems(Context.getInstance().getRegisterComponent().getChooseComponentList());
        tableViewVersion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            addToCar();
        });
    }

    public void addToCar(){

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

    public void loadChoice(){
        modelChoice.removeAll();
        modelChoice.addAll("Elektrisk", "Diesel", "Hybrid");
        cbModel.getItems().addAll(modelChoice);
        cbModel.setPromptText("Velg modell");


    }
}
