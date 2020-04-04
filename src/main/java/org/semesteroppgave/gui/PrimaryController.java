package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.semesteroppgave.Main;

import java.io.IOException;

public class PrimaryController {

    @FXML
    void btnAdmin(ActionEvent event) throws IOException {
        Main.setRoot("adminsignin");
    }

    @FXML
    void btnUser(ActionEvent event) throws IOException {
        Main.setRoot("usersignin");
    }
}
