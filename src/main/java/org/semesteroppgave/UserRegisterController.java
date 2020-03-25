package org.semesteroppgave;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserRegisterController {

    @FXML
    private TextField txtUsername, txtPhonenumber, txtPassword, txtEmail, txtName;

    @FXML
    void btnRegister(ActionEvent event) {

    }

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Main.setRoot("usersignin");
    }
}
