package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.semesteroppgave.Main;
import org.semesteroppgave.exceptions.*;
import org.semesteroppgave.signin.PersonLogin;
import org.semesteroppgave.signin.Admin;
import org.semesteroppgave.signin.PersonValidator;

import java.io.IOException;

public class AdminSignInController {

    @FXML
    private TextField txtUsernameSignin;

    @FXML
    private TextField txtPasswordSignin;

    @FXML
    private Label lblSignin;

    @FXML
    private TextField txtEmpNo;

    @FXML
    private TextField txtUsernameRegister;

    @FXML
    private TextField txtPasswordRegister;

    @FXML
    private Label lblRegister;

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Main.setRoot("usersignin");
    }

    @FXML
    void btnRegister(ActionEvent event) {
        lblRegister.setText("");

        // Sjekker om admin-brukernavnet finnes fra før
        if (PersonLogin.checkIfNotExisting(txtUsernameRegister.getText(), "adminUsernameAndPassword")) {

            // Prøver å opprette ny admin
            try {
                if(PersonValidator.testValidEmpNo(txtEmpNo.getText())){
                    Admin newAdmin = new Admin(txtUsernameRegister.getText(), txtPasswordRegister.getText(), txtEmpNo.getText());
                    PersonLogin.setAdminList(newAdmin);
                    Dialogs.showSuccessDialog("Ny admin", "Ny admin ble registrert", "Logg inn med brukernavn og passord");
                    PersonLogin.saveToFileUsernamePassword("adminUsernameAndPassword");
                    PersonLogin.saveToFileInfo("adminInfo");
                    PersonValidator.getAvailableEmpNos().remove(txtEmpNo.getText());
                    Main.setRoot("adminsignin");
                }else {
                    lblRegister.setText("Ansattnummeret er opptatt eller finnes ikke!\nPrøv et annet.");
                }
            } catch (InvalidPhonenumberException | InvalidEmailException | InvalidNameException | InvalidUsernameException | InvalidPasswordException | InvalidEmployeeNoException | IOException e) {
                lblRegister.setText(e.getMessage());
            }

        } else {
            lblRegister.setText("Brukeren finnes fra før\n velg et annet brukernavn");
        }
    }

    @FXML
    void btnSignin(ActionEvent event) throws IOException {
        // Henter login-info fra admin-filen
        String file = "adminUsernameAndPassword";
        if(PersonLogin.verifyLogin(txtUsernameSignin.getText(), txtPasswordSignin.getText(), file)) {
            //Setter aktiv admin
            for (Admin admin : PersonLogin.getAdminList()){
                if (admin.getPassword().equals(txtPasswordSignin.getText())){
                    PersonLogin.setActiveAdminId(admin.getEmployeeId());
                }
            }
            Main.setRoot("admincomponent");
        }else {
            lblSignin.setText("Feil brukernavn og/eller passord");
        }
    }

}
