package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.semesteroppgave.Main;
import org.semesteroppgave.PersonLogin;
import org.semesteroppgave.exceptions.*;
import org.semesteroppgave.personsUserAdmin.Admin;
import org.semesteroppgave.personsUserAdmin.PersonValidator;

import java.io.IOException;

import static org.semesteroppgave.personsUserAdmin.Admin.adminList;

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
                    Dialogs.showSuccessDialog("Ny admin", "Ny admin ble registrert", "Gå tilbake til start og logg inn med brukernavn og passord");

                    clearInput();

                    System.out.println("Admins:");
                    for (Admin admin : adminList) {
                        System.out.println("-->\t"+admin.getUsername()+"\t"+admin.getPassword()+"\t"+admin.getEmployeeId());
                    }
                    Admin.saveToFileUsernamePassword();
                    Admin.saveToFileAdminInfo();
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
    private void clearInput(){
        txtEmployeenumber.clear();
        txtUsername.clear();
        txtPassword.clear();
    }
}
