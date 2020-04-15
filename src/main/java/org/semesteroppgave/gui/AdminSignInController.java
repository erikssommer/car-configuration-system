package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.semesteroppgave.Main;
import org.semesteroppgave.signin.PersonLogin;
import org.semesteroppgave.signin.Admin;

import java.io.IOException;

public class AdminSignInController {

    @FXML
    private TextField txtUsername, txtPassword;

    @FXML
    private Label lblFeedback;

    @FXML
    void btnRegister(ActionEvent event) throws IOException {
        Main.setRoot("adminregister");
    }

    @FXML
    void btnSignin(ActionEvent event) throws IOException {
        // Henter login-info fra admin-filen
        String file = "adminUsernameAndPassword";
        if(PersonLogin.verifyLogin(txtUsername.getText(), txtPassword.getText(), file)) {
            //Setter aktiv admin
            for (Admin admin : PersonLogin.getAdminList()){
                if (admin.getPassword().equals(txtPassword.getText())){
                    PersonLogin.setActiveAdminId(admin.getEmployeeId());
                }
            }
            Main.setRoot("admincomponent");
        }else {
            lblFeedback.setText("Feil brukernavn og/eller passord");
        }
    }

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Main.setRoot("primary");
    }

}
