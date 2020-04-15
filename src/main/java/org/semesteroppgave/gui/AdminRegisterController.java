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

import static org.semesteroppgave.signin.Admin.adminList;

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
        if (!PersonLogin.checkIfExisting(txtUsername.getText(), "adminUsernameAndPassword")) {

            // Prøver å opprette ny admin
            try {
                if(PersonValidator.testValidEmpNo(txtEmployeenumber.getText())){
                    Admin newAdmin = new Admin(txtUsername.getText(), txtPassword.getText(), txtEmployeenumber.getText());
                    Admin.registerAdmin(newAdmin);
                    //PersonValidator.removeCurrentEmpNo(newAdmin.getEmployeeId());
                    lblFeedback.setText("Ny superbruker registrert på ansattnr: " + txtEmployeenumber.getText() + "\nBrukernavn: " + txtUsername.getText() + "\nPassord: " + txtPassword.getText());
                    Dialogs.showSuccessDialog("Ny admin", "Ny admin ble registrert", "Logg inn med brukernavn og passord");
                    Admin.saveToFileUsernamePassword();
                    Admin.saveToFileAdminInfo();
                    PersonValidator.availableEmpNos.remove(txtEmployeenumber.getText());
                    Main.setRoot("adminsignin");
                }else {
                    lblFeedback.setText("Ansattnummeret er opptatt eller finnes ikke!\nPrøv et annet.");
                }
            } catch (InvalidPhonenumberException | InvalidEmailException | InvalidNameException | InvalidUsernameException | InvalidPasswordException | InvalidEmployeeNoException e) {
                lblFeedback.setText(e.getMessage());
                System.out.print(e.getMessage());
            }

        } else {
            lblFeedback.setText("Brukeren finnes fra før\n velg et annet brukernavn");
        }
    }
}
