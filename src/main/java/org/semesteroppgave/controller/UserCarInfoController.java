package org.semesteroppgave.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.Main;

import java.io.IOException;

public class UserCarInfoController {

    @FXML
    private TextArea txtInfo;

    public void initialize() {
        txtInfo.setText(ApplicationData.getInstance().getRegisterProduct().getSelectedCar().toString());
    }

    @FXML
    private void btnBack(ActionEvent event) throws IOException {
        Main.setRoot("configuredcars");
    }

}
