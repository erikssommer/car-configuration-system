package org.semesteroppgave.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.signin.admin.Admin;
import org.semesteroppgave.models.signin.admin.AdminSignIn;

import java.io.IOException;

public class AdminSignInController implements ApplicationController{

    private final AdminSignIn adminSignin = new AdminSignIn();

    @FXML
    private TextField txtUsernameSignin, txtPasswordSignin, txtEmpNo, txtUsernameRegister, txtPasswordRegister;

    @FXML
    private Label lblSignin, lblRegister;

    @Override
    public void initialize() {
        adminSignin.initializeEmpNos();
        adminSignin.parseExistingAdmins();
    }

    @FXML
    private void btnCancel() throws IOException {
        Main.setRoot("usersignin");
    }

    @FXML
    private void btnRegister() {
        lblRegister.setText("");

        // Prøver å opprette ny admin
        try {
            adminSignin.register(txtUsernameRegister.getText(), txtPasswordRegister.getText(), txtEmpNo.getText());

        } catch (IllegalArgumentException | IOException e) {
            lblRegister.setText(e.getMessage());
        }
    }

    @FXML
    private void btnSignin() throws IOException {
        // Henter login-info fra admin-filen
        if (adminSignin.verifyLogin(txtUsernameSignin.getText(), txtPasswordSignin.getText())) {
            //Setter aktiv admin
            for (Admin admin : adminSignin.getAdminList()) {
                if (admin.getPassword().equals(txtPasswordSignin.getText())) {
                    AdminSignIn.setActiveAdminId(admin.getEmployeeId());
                }
            }
            Main.setRoot("admincomponent");
        } else {
            lblSignin.setText("Feil brukernavn og/eller passord");
        }
    }
}
