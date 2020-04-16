package org.semesteroppgave.gui;

import java.io.IOException;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.semesteroppgave.Context;
import org.semesteroppgave.Main;
import org.semesteroppgave.OpenWithThread;
import org.semesteroppgave.exceptions.*;
import org.semesteroppgave.signin.ParseUsernamePassword;
import org.semesteroppgave.signin.PersonLogin;
import org.semesteroppgave.signin.PersonValidator;
import org.semesteroppgave.signin.User;

public class UserSignInController {

    ParseUsernamePassword parse = new ParseUsernamePassword();

    private OpenWithThread thread;

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

    public void initialize(){
        parse.parseExistingUser();
        if (Context.getInstance().getRegisterComponent().getComponentsList().isEmpty()){
            progressbar.setVisible(true);
            startThread();
        }else {
            progressbar.setVisible(false);
        }
    }

    @FXML
    void btnAdmin(ActionEvent event) throws IOException {
        PersonValidator.initializeEmpNos();
        parse.parseExistingAdmins();
        Main.setRoot("adminsignin");
    }

    @FXML
    void btnRegister(ActionEvent event) {
        lblRegister.setText("");
        // Sjekker at brukeren ikke finnes fra før
        if(PersonLogin.checkIfNotExisting(txtUsernameRegister.getText(), "userUsernameAndPassword")){

            // Prøver å opprette ny bruker hvis feltene er fylt inn
            try {
                User newUser = new User(txtUsernameRegister.getText(), txtPasswordRegister.getText(), txtName.getText(), txtPhonenumber.getText(), txtEmail.getText());
                PersonLogin.setUserList(newUser);
                Dialogs.showSuccessDialog("Ny bruker", "Ny bruker ble registrert", "Logg inn med brukernavn og passord");
                PersonLogin.saveToFileUsernamePassword("userUsernameAndPassword");
                PersonLogin.saveToFileInfo("userInfo");
                Main.setRoot("usersignin");

            } catch (InvalidPhonenumberException | InvalidEmailException | InvalidNameException | InvalidUsernameException | InvalidPasswordException | IOException e) {
                lblRegister.setText(e.getMessage());
            }
        }

        else{
            // Feilmelding hvis brukernavn er opptatt
            lblRegister.setText("Brukeren finnes fra før\nVelg et annet brukernavn");
        }
    }

    @FXML
    void btnSignin(ActionEvent event) throws IOException {
        // Henter login-info fra user-filen
        String file = "userUsernameAndPassword";
        if(PersonLogin.verifyLogin(txtUsernameLogin.getText(), txtPasswordLogin.getText(), file)) {
            Main.setRoot("userbuildcar");
        }else {
            lblSignin.setText("Feil brukernavn og/eller passord");
        }
    }

    public void startThread(){

        lblThreadMessage.setText("Laster inn fil...");
        thread = new OpenWithThread(progressbar);
        thread.setOnSucceeded(this::fileOpened);
        thread.setOnFailed(this::fileOpeningFailed);
        Thread th = new Thread(thread);
        th.setDaemon(true);
        disableGUI();
        th.start();
    }

    private void fileOpened(WorkerStateEvent e) {
        lblThreadMessage.setText("Ferdig");
        enableGUI();
    }

    private void fileOpeningFailed(WorkerStateEvent e) {
        lblThreadMessage.setText(e.getSource().getException().getMessage());
        enableGUI();
    }

    private void disableGUI(){
        tabRegister.setDisable(true);
        txtPasswordLogin.setDisable(true);
        txtUsernameLogin.setDisable(true);
        btnSignin.setDisable(true);
        btnAdmin.setDisable(true);
    }

    private void enableGUI(){
        tabRegister.setDisable(false);
        txtPasswordLogin.setDisable(false);
        txtUsernameLogin.setDisable(false);
        btnSignin.setDisable(false);
        btnAdmin.setDisable(false);

    }
}