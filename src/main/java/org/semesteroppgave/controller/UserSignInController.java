package org.semesteroppgave.controller;

import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.signin.user.User;
import org.semesteroppgave.models.signin.user.UserSignIn;
import org.semesteroppgave.models.utilities.alerts.Dialogs;
import org.semesteroppgave.models.utilities.helpers.OpenWithThread;

import java.io.IOException;

public class UserSignInController {

    private final UserSignIn userSignIn = new UserSignIn();

    @FXML
    private Tab tabRegister;

    @FXML
    private Button btnAdmin, btnSignin;

    @FXML
    private TextField txtUsernameLogin, txtPasswordLogin;

    @FXML
    private Label lblSignin, lblRegister;

    @FXML
    private TextField txtName, txtEmail, txtPhonenumber, txtUsernameRegister, txtPasswordRegister;

    @FXML
    private Label lblThreadMessage;

    @FXML
    private ProgressBar progressbar;

    public void initialize() {
        userSignIn.parseExistingUser();
        if (ApplicationData.getInstance().getRegisterComponent().getComponentList().isEmpty()) {
            progressbar.setVisible(true);
            startThread();
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
        } else {
            lblSignin.setText("Feil brukernavn og/eller passord");
        }
    }

    @FXML
    private void tabRegisterSelected() {
        progressbar.setVisible(false);
        lblThreadMessage.setVisible(false);
    }

    private void startThread() {

        lblThreadMessage.setText("Laster inn fil...");
        OpenWithThread openWithThread = new OpenWithThread(progressbar, Main.class.getResource("files/onApplicationLaunch/komponenter.jobj").getFile());
        openWithThread.setOnSucceeded(this::fileOpened);
        openWithThread.setOnFailed(this::fileOpeningFailed);
        Thread thread = new Thread(openWithThread);
        thread.setDaemon(true);
        disableGUI();
        thread.start();
    }

    private void fileOpened(WorkerStateEvent e) {
        lblThreadMessage.setText("Ferdig");
        enableGUI();
    }

    private void fileOpeningFailed(WorkerStateEvent e) {
        lblThreadMessage.setText("Feil i tråd");
        Dialogs.showErrorDialog("Fil", "Feil i åpning av fil", e.getSource().getException().getMessage());
        enableGUI();
    }

    private void disableGUI() {
        tabRegister.setDisable(true);
        txtPasswordLogin.setDisable(true);
        txtUsernameLogin.setDisable(true);
        btnSignin.setDisable(true);
        btnAdmin.setDisable(true);
    }

    private void enableGUI() {
        tabRegister.setDisable(false);
        txtPasswordLogin.setDisable(false);
        txtUsernameLogin.setDisable(false);
        btnSignin.setDisable(false);
        btnAdmin.setDisable(false);

    }
}