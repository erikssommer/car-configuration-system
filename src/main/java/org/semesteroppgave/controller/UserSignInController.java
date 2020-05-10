package org.semesteroppgave.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.signin.user.User;
import org.semesteroppgave.models.signin.user.UserSignIn;
import org.semesteroppgave.models.utilities.alerts.Dialogs;
import org.semesteroppgave.models.utilities.threadhelper.StartThread;

import java.io.IOException;

public class UserSignInController implements ApplicationThread {

    private final UserSignIn userSignIn = new UserSignIn();

    @FXML
    private Tab tabRegister;

    @FXML
    private Button btnAdmin, btnSignin;

    @FXML
    private Label lblSignin, lblRegister, lblThreadMessage;

    @FXML
    private TextField txtName, txtEmail, txtPhonenumber, txtUsernameRegister, txtPasswordRegister, txtUsernameLogin, txtPasswordLogin;

    @FXML
    private ProgressBar progressbar;

    @Override
    public void initialize() {
        StartThread startThread = new StartThread(this, lblThreadMessage, progressbar);
        userSignIn.parseExistingUser();
        if (ApplicationData.getInstance().getRegisterComponent().getComponentList().isEmpty()) {
            progressbar.setVisible(true);
            startThread.start(getClass().getResource("/org/semesteroppgave/files/onApplicationLaunch/components.jobj").getPath());
        } else {
            progressbar.setVisible(false);
        }
    }

    @FXML
    private void btnAdmin() throws IOException {
        Main.setRoot("adminsignin");
    }

    @FXML
    private void btnRegister() {
        lblRegister.setText("");
        try {
            userSignIn.register(txtUsernameRegister.getText(), txtPasswordRegister.getText(),
                    txtName.getText(), txtPhonenumber.getText(), txtEmail.getText());

        } catch (IllegalArgumentException | IOException e) {
            lblRegister.setText(e.getMessage());
        }
    }

    @FXML
    private void btnSignin() throws IOException {
        // Henter login-info fra user-filen
        if (userSignIn.verifyLogin(txtUsernameLogin.getText(), txtPasswordLogin.getText())) {
            //Setter aktivt brukernavn
            for (User user : userSignIn.getUserList()) {
                if (user.getPassword().equals(txtPasswordLogin.getText())) {
                    UserSignIn.setActiveUsername(user.getUsername());
                }
            }
            Main.setRoot("userbuildproduct");
            if (ApplicationData.getInstance().getRegisterComponent().getComponentList().isEmpty()) {
                Dialogs.showWarningDialog("Varsel", "Ingen komponenter",
                        "Admin må opprette komponenter før du kan bygge en bil");
            }
        } else {
            lblSignin.setText("Feil brukernavn og/eller passord");
        }
    }

    @FXML
    private void tabRegisterSelected() {
        progressbar.setVisible(false);
        lblThreadMessage.setVisible(false);
    }

    @Override
    public void disableGUI() {
        tabRegister.setDisable(true);
        txtPasswordLogin.setDisable(true);
        txtUsernameLogin.setDisable(true);
        btnSignin.setDisable(true);
        btnAdmin.setDisable(true);
    }

    @Override
    public void enableGUI() {
        tabRegister.setDisable(false);
        txtPasswordLogin.setDisable(false);
        txtUsernameLogin.setDisable(false);
        btnSignin.setDisable(false);
        btnAdmin.setDisable(false);
    }
}