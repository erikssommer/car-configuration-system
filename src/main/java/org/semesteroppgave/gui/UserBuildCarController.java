package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import org.semesteroppgave.carcomponents.Component;
import org.semesteroppgave.Main;

import java.io.IOException;

public class UserBuildCarController {

    @FXML
    private TableView<Component> tableViewComponent;

    @FXML
    private TableView<Component> tableViewVersion;

    @FXML
    private TextField txtTotalPrice;

    @FXML
    private Label lblMessage;

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
    void cbAutopilot(ActionEvent event) {

    }

    @FXML
    void cbGps(ActionEvent event) {

    }

    @FXML
    void cbHitch(ActionEvent event) {

    }

    @FXML
    void cbSunroof(ActionEvent event) {

    }

    @FXML
    void choiseMade(ContextMenuEvent event) {

    }


}
