package org.semesteroppgave.signin;

import org.semesteroppgave.gui.Dialogs;
import org.semesteroppgave.exceptions.InvalidPasswordException;
import org.semesteroppgave.exceptions.InvalidUsernameException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;


public class ParseUsernamePassword {

    // Leser inn eksisterende brukere fra bruker-filene og legger de i en ArrayList.
    public void parseExistingUser() {
        PersonLogin.getUserList().clear();
            // Tar inn info fra bruker-filer
            var filepathUnamePword = Paths.get("src/main/java/org/semesteroppgave/signin/loginFiles", "userUsernameAndPassword");
            var filepathUInfo = Paths.get("src/main/java/org/semesteroppgave/signin/loginFiles", "userInfo");

            try {
                Scanner s = new Scanner(new File(String.valueOf(filepathUInfo)));
                Scanner g = new Scanner(new File(String.valueOf(filepathUnamePword)));

                s.useDelimiter("[,\n]");
                g.useDelimiter("[,\n]");

                while (s.hasNext() && g.hasNext()) {
                    String name = s.next();
                    String phonenumber = s.next();
                    String email = s.next();
                    String username = g.next();
                    String password = g.next();
                    // Oppretter bruker fra fil og legger i userList
                    User newUser = new User(username, password, name, phonenumber, email);
                    PersonLogin.setUserList(newUser);
                }
            } catch (InvalidUsernameException | InvalidPasswordException | FileNotFoundException e) {
                Dialogs.showErrorDialog("Oups", "En feil har skjedd ved parsing av brukerfiler", e.getMessage());
            }
    }

    public void parseExistingAdmins() {
        PersonLogin.getAdminList().clear();
        var filepathAInfo = Paths.get("src/main/java/org/semesteroppgave/signin/loginFiles", "adminInfo");
        var filepathAUnamePword = Paths.get("src/main/java/org/semesteroppgave/signin/loginFiles", "adminUsernameAndPassword");

        try {
            Scanner info = new Scanner(new File(String.valueOf(filepathAInfo)));
            Scanner unamePword = new Scanner(new File(String.valueOf(filepathAUnamePword)));

            info.useDelimiter("[\n]");
            unamePword.useDelimiter("[,\n]");

            while (info.hasNext() && unamePword.hasNext()) {
                String empNo = info.next();
                String username = unamePword.next();
                String password = unamePword.next();
                // Oppretter admin fra fil og legger i userList
                Admin newAdmin = new Admin(username, password, empNo);
                PersonLogin.getAdminList().add(newAdmin);
                PersonValidator.getAvailableEmpNos().remove(empNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
