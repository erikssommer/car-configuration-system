package org.semesteroppgave.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.exceptions.*;
import org.semesteroppgave.models.signin.*;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.IOException;

public class AdminSignInController {

    AdminSignin adminSignin = new AdminSignin();

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

    public void initialize(){
        adminSignin.initializeEmpNos();
        adminSignin.parseExistingAdmins();
    }

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Main.setRoot("usersignin");
    }

    @FXML
    void btnRegister(ActionEvent event) {
        lblRegister.setText("");

        // Sjekker om admin-brukernavnet finnes fra før
        if (adminSignin.checkIfNotExisting(txtUsernameRegister.getText())) {

            // Prøver å opprette ny admin
            try {
                if(adminSignin.testValidEmpNo(txtEmpNo.getText())){
                    adminSignin.register(txtUsernameRegister.getText(), txtPasswordRegister.getText(), txtEmpNo.getText());
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
        if(adminSignin.verifyLogin(txtUsernameSignin.getText(), txtPasswordSignin.getText())) {
            //Setter aktiv admin
            for (Admin admin : adminSignin.getAdminList()){
                if (admin.getPassword().equals(txtPasswordSignin.getText())){
                    AdminSignin.setActiveAdminId(admin.getEmployeeId());
                }
            }
            Main.setRoot("admincomponent");
        }else {
            lblSignin.setText("Feil brukernavn og/eller passord");
        }
    }
}
