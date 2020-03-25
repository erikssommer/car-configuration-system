package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.semesteroppgave.Main;

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
