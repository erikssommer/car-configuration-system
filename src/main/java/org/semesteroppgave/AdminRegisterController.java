package org.semesteroppgave;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminRegisterController {

    @FXML
    private TextField txtEmployeenumber, txtUsername, txtPassword;

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Main.setRoot("adminsignin");
    }

    @FXML
    void btnRegister(ActionEvent event) throws IOException {
        Main.setRoot("adminsignin");
    }
}
