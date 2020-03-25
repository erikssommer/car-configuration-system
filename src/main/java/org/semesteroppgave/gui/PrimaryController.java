package org.semesteroppgave.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.semesteroppgave.Main;

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
