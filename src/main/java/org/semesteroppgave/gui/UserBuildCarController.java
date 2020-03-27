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
    private TextField txtTotalPrice;

    @FXML
    private Label lblMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                    case "Elektrisk": tableViewComponent.setItems(null);
                        break;
                    case "Diesel": tableViewComponent.setItems(null);
                        break;
                    case "Hybrid": tableViewComponent.setItems(null);
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

    public void loadChoice(){
        modelChoice.removeAll();
        modelChoice.addAll("Elektrisk", "Diesel", "Hybrid");
        cbModel.getItems().addAll(modelChoice);
        cbModel.setPromptText("Velg modell");


    }
}
