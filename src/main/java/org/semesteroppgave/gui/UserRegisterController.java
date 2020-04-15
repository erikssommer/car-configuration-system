package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.semesteroppgave.Main;
import org.semesteroppgave.signin.PersonLogin;
import org.semesteroppgave.exceptions.*;
import org.semesteroppgave.signin.User;

import java.io.IOException;

public class UserRegisterController {

    @FXML
    private TextField txtUsername, txtPhonenumber, txtPassword, txtEmail, txtName;

    @FXML
    private Label lblFeedback;

    @FXML
    void btnRegister(ActionEvent event) {
        lblFeedback.setText("");
        // Sjekker at brukeren ikke finnes fra før
        if(PersonLogin.checkIfNotExisting(txtUsername.getText(), "userUsernameAndPassword")){

            // Prøver å opprette ny bruker hvis feltene er fylt inn
            try {
                User newUser = new User(txtUsername.getText(), txtPassword.getText(), txtName.getText(), txtPhonenumber.getText(), txtEmail.getText());
                PersonLogin.setUserList(newUser);
                Dialogs.showSuccessDialog("Ny bruker", "Ny bruker ble registrert", "Logg inn med brukernavn og passord");
                PersonLogin.saveToFileUsernamePassword("userUsernameAndPassword");
                PersonLogin.saveToFileInfo("userInfo");
                Main.setRoot("usersignin");

            } catch (InvalidPhonenumberException | InvalidEmailException | InvalidNameException | InvalidUsernameException | InvalidPasswordException | IOException e) {
                lblFeedback.setText(e.getMessage());
            }
        }

        else{
            // Feilmelding hvis brukernavn er opptatt
            lblFeedback.setText("Brukeren finnes fra før\nVelg et annet brukernavn");
        }
    }

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Main.setRoot("usersignin");
    }
}
