package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import org.semesteroppgave.Component;
import org.semesteroppgave.Main;

import java.io.IOException;

public class AdminComponentController {

    @FXML
    private Label lblAdminID;

    @FXML
    private TableView<Component> tableViewComponents;

    @FXML
    private ChoiceBox<String> cbFilter;

    @FXML
    private ChoiceBox<String> cbCreate;

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

    @FXML
    void editModel(TableColumn.CellEditEvent<Component, String> event) {

    }

    @FXML
    void editPrice(TableColumn.CellEditEvent<Component, Double> event) {

    }

    @FXML
    void editVersion(TableColumn.CellEditEvent<Component, String> event) {

    }
}
