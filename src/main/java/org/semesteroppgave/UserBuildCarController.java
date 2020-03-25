package org.semesteroppgave;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;

import java.io.IOException;

public class UserBuildCarController {

    @FXML
    private TableView<?> tableViewComponent;

    @FXML
    private TableView<?> tableViewVersion;

    @FXML
    private TextField txtTotalPrice;

    @FXML
    private Label lblMessage;

    @FXML
    void btnShowConfigs(ActionEvent event) {

    }

    @FXML
    void btnShowMyConfig(ActionEvent event) {

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
