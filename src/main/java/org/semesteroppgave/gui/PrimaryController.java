package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.semesteroppgave.Main;
import org.semesteroppgave.personsUserAdmin.ParseUsernamePassword;
import org.semesteroppgave.personsUserAdmin.PersonValidator;

import java.io.IOException;

public class PrimaryController {
    ParseUsernamePassword parse = new ParseUsernamePassword();

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
        Main.setRoot("usersignin");
    }
}
