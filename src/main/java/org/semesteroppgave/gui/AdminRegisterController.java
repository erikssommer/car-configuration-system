package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.semesteroppgave.Main;
import org.semesteroppgave.signin.PersonLogin;
import org.semesteroppgave.exceptions.*;
import org.semesteroppgave.signin.Admin;
import org.semesteroppgave.signin.PersonValidator;

import java.io.IOException;

public class AdminRegisterController {

    @FXML
    private Label lblFeedback;

    @FXML
    private TextField txtEmployeenumber, txtUsername, txtPassword;

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Main.setRoot("adminsignin");
    }

    @FXML
    void btnRegister(ActionEvent event) throws IOException {
        lblFeedback.setText("");

        // Sjekker om admin-brukernavnet finnes fra før
        if (PersonLogin.checkIfNotExisting(txtUsername.getText(), "adminUsernameAndPassword")) {

            // Prøver å opprette ny admin
            try {
                if(PersonValidator.testValidEmpNo(txtEmployeenumber.getText())){
                    Admin newAdmin = new Admin(txtUsername.getText(), txtPassword.getText(), txtEmployeenumber.getText());
                    PersonLogin.setAdminList(newAdmin);
                    Dialogs.showSuccessDialog("Ny admin", "Ny admin ble registrert", "Logg inn med brukernavn og passord");
                    PersonLogin.saveToFileUsernamePassword("adminUsernameAndPassword");
                    PersonLogin.saveToFileInfo("adminInfo");
                    PersonValidator.getAvailableEmpNos().remove(txtEmployeenumber.getText());
                    Main.setRoot("adminsignin");
                }else {
                    lblFeedback.setText("Ansattnummeret er opptatt eller finnes ikke!\nPrøv et annet.");
                }
            } catch (InvalidPhonenumberException | InvalidEmailException | InvalidNameException | InvalidUsernameException | InvalidPasswordException | InvalidEmployeeNoException e) {
                lblFeedback.setText(e.getMessage());
            }

        } else {
            lblFeedback.setText("Brukeren finnes fra før\n velg et annet brukernavn");
        }
    }
}
