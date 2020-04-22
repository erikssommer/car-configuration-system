package org.semesteroppgave.controller;

import java.io.IOException;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.utilities.helpers.OpenWithThread;
import org.semesteroppgave.models.exceptions.*;
import org.semesteroppgave.models.signin.UserSignIn;

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
        if (ApplicationData.getInstance().getRegisterComponent().getComponentsList().isEmpty()) {
            progressbar.setVisible(true);
            startThread();
        } else {
            progressbar.setVisible(false);
        }
    }

    @FXML
    private void btnAdmin(ActionEvent event) throws IOException {
        Main.setRoot("adminsignin");
    }

    @FXML
    private void btnRegister(ActionEvent event) {
        lblRegister.setText("");
        // Sjekker at brukeren ikke finnes fra før
        if (userSignIn.checkIfNotExisting(txtUsernameRegister.getText())) {

            // Prøver å opprette ny bruker hvis feltene er fylt inn
            try {
                userSignIn.register(txtUsernameRegister.getText(), txtPasswordRegister.getText(), txtName.getText(), txtPhonenumber.getText(), txtEmail.getText());

            } catch (InvalidPhonenumberException | InvalidEmailException | InvalidNameException | InvalidUsernameException | InvalidPasswordException | IOException e) {
                lblRegister.setText(e.getMessage());
            }
        } else {
            // Feilmelding hvis brukernavn er opptatt
            lblRegister.setText("Brukeren finnes fra før\nVelg et annet brukernavn");
        }
    }

    @FXML
    private void btnSignin(ActionEvent event) throws IOException {
        // Henter login-info fra user-filen
        if (userSignIn.verifyLogin(txtUsernameLogin.getText(), txtPasswordLogin.getText())) {
            Main.setRoot("userbuildcar");
        } else {
            lblSignin.setText("Feil brukernavn og/eller passord");
        }
    }

    @FXML
    private void tabRegisterSelected(Event event) {
        progressbar.setVisible(false);
        lblThreadMessage.setVisible(false);
    }

    private void startThread() {

        lblThreadMessage.setText("Laster inn fil...");
        OpenWithThread openWithThread = new OpenWithThread(progressbar, "files/komponenter.jobj");
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
        lblThreadMessage.setText(e.getSource().getException().getMessage());
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