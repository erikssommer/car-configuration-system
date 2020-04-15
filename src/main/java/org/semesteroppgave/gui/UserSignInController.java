package org.semesteroppgave.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.semesteroppgave.Main;
import org.semesteroppgave.signin.PersonLogin;

public class UserSignInController {

    @FXML
    private TextField txtUsername, txtPassword;

    @FXML
    private Label lblFeedback;

    @FXML
    void btnRegister(ActionEvent event) throws IOException {
        Main.setRoot("userregister");
    }

    @FXML
    void btnSignin(ActionEvent event) throws IOException {
        // Henter login-info fra user-filen
        String file = "userUsernameAndPassword";
        if(PersonLogin.verifyLogin(txtUsername.getText(), txtPassword.getText(), file)) {
            Main.setRoot("userbuildcar");
        }else {
            lblFeedback.setText("Feil brukernavn og/eller passord");
        }
    }

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Main.setRoot("primary");
    }
}