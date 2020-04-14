package org.semesteroppgave.gui;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.semesteroppgave.Context;
import org.semesteroppgave.Main;
import org.semesteroppgave.OpenWithThread;
import org.semesteroppgave.personsUserAdmin.ParseUsernamePassword;
import org.semesteroppgave.personsUserAdmin.PersonValidator;

import java.io.IOException;

public class PrimaryController {

    ParseUsernamePassword parse = new ParseUsernamePassword();

    private OpenWithThread thread;

    @FXML
    private Button btnAdmin, btnUser;

    @FXML
    private Label lblThreadMessage;

    @FXML
    private ProgressBar progressbar;

    public void initialize(){
        if (Context.getInstance().getRegisterComponent().getComponentsList().isEmpty()){
            progressbar.setVisible(true);
            startThread();
        }else {
            progressbar.setVisible(false);
        }
    }

    @FXML
    void btnAdmin(ActionEvent event) throws IOException {
        parse.parseExistingAdmins();
        PersonValidator.initializeEmpNos();
        Main.setRoot("adminsignin");
    }

    @FXML
    void btnUser(ActionEvent event) throws IOException {
        parse.parseExistingUser();
        Main.setRoot("usersignin");
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
        btnAdmin.setDisable(true);
        btnUser.setDisable(true);
    }

    private void enableGUI(){
        btnAdmin.setDisable(false);
        btnUser.setDisable(false);
    }
}
