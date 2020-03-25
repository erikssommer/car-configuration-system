package org.semesteroppgave;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    void btnAdmin(ActionEvent event) {

    }

    @FXML
    void btnUser(ActionEvent event) throws IOException {
        Main.setRoot("usersignin");
    }
}
