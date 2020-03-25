package org.semesteroppgave;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class UserCarInfoController {
    @FXML
    private TextArea txtInfo;

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Main.setRoot("configuredcars");
    }
}
