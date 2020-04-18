package org.semesteroppgave.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.semesteroppgave.Context;
import org.semesteroppgave.Main;

import java.io.IOException;

public class UserCarInfoController {

    @FXML
    private TextArea txtInfo;

    public void initialize() {
        txtInfo.setText(Context.getInstance().getRegisterProduct().getSelectedCar().toString());
    }

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Main.setRoot("configuredcars");
    }

}
