package org.semesteroppgave;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class AdminComponentController {

    @FXML
    private Label lblAdminID;

    @FXML
    private TableView<?> tableViewComponents;

    @FXML
    private ChoiceBox<?> cbFilter;

    @FXML
    private ChoiceBox<?> cbCreate;

    @FXML
    void btnCreate(ActionEvent event) throws IOException {
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
}
