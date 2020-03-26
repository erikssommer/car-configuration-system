package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.semesteroppgave.Main;

import java.io.IOException;

public class UserCarInfoController {

    @FXML
    private TextArea txtInfo;

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Main.setRoot("configuredcars");
    }
}
