package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.semesteroppgave.Context;
import org.semesteroppgave.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserCarInfoController implements Initializable {
    @FXML
    private TextArea txtInfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtInfo.setText(Context.getInstance().getRegisterProduct().getSelectedCar().toString());
    }

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Main.setRoot("configuredcars");
    }

}
