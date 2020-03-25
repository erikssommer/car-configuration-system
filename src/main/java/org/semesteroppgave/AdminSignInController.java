package org.semesteroppgave;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminSignInController {

    @FXML
    private TextField txtUsername, txtPassword;

    @FXML
    void btnRegister(ActionEvent event) throws IOException {
        Main.setRoot("adminregister");
    }

    @FXML
    void btnSignin(ActionEvent event) throws IOException {
        Main.setRoot("admincomponent");
    }

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Main.setRoot("primary");
    }

}
