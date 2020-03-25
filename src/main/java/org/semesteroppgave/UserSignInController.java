package org.semesteroppgave;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UserSignInController {

    @FXML
    private TextField txtUsername, txtPassword;

    @FXML
    void btnRegister(ActionEvent event) {

    }

    @FXML
    void btnSignin(ActionEvent event) {

    }

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Main.setRoot("primary");
    }
}