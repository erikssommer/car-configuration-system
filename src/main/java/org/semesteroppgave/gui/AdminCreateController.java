package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.semesteroppgave.Context;
import org.semesteroppgave.Main;
import org.semesteroppgave.carcomponents.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminCreateController implements Initializable {
    @FXML
    private Label lblComponent;

    @FXML
    private TextField txtVersion, txtPrice;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TableView<Component> tableViewAddedConfig;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblComponent.setText(Context.getInstance().getRegisterComponent().getNewComponent().toLowerCase());
    }

    @FXML
    void btnAdd(ActionEvent event) {

    }

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Main.setRoot("admincomponent");
    }

    @FXML
    void btnComplete(ActionEvent event) {

    }
}
