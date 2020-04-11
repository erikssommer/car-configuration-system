package org.semesteroppgave.personsUserAdmin;

import org.semesteroppgave.gui.Dialogs;
import org.semesteroppgave.exceptions.InvalidPasswordException;
import org.semesteroppgave.exceptions.InvalidUsernameException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.semesteroppgave.personsUserAdmin.Admin.adminList;
import static org.semesteroppgave.personsUserAdmin.User.userList;

public class ParseUsernamePassword {

    // Leser inn eksisterende brukere fra bruker-filene og legger de i en ArrayList.
    public void parseExistingUser() {
        userList.clear();
            // Tar inn info fra bruker-filer
            var filepathUnamePword = Paths.get("src/main/java/org/semesteroppgave/loginFiles", "userUsernameAndPassword");
            var filepathUInfo = Paths.get("src/main/java/org/semesteroppgave/loginFiles", "userInfo");

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
                    userList.add(newUser);
                }
                //parseSaveToFile();
            } catch (InvalidUsernameException | InvalidPasswordException | FileNotFoundException e) {
                Dialogs.showErrorDialog("Oups", "En feil har skjedd ved parsing av brukerfiler", e.getMessage());
            }
    }
    // Legger inn brukernavn og passord inn i filen igjen
    public static String parseUnamePwordTxtToFile() {
        StringBuilder ut = new StringBuilder();
        for (User newUser : userList) {
            ut.append(newUser.printTxtUsernamePassword()).append("\n");
        }
        return ut.toString();
    }

    // Legger inn bruker-info inn i filen igjen
    public static String parseUserInfoTxtToFile() {
        StringBuilder ut = new StringBuilder();
        for (User newUser : userList) {
            ut.append(newUser.printTxtUserInfo()).append("\n");
        }
        return ut.toString();
    }

    // Lagrer bruker-info til de to filene
    private static void parseSaveToFile(){
        // Lagrer eksisterenede bruker-info til fil
        var filepathInfo = Paths.get("src/main/java/org/semesteroppgave/loginFiles", "userInfo");
        try {
            Files.write(Paths.get(String.valueOf(filepathInfo)), parseUserInfoTxtToFile().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        // Larger eksisterenede brukernavn og passord til fil
        var filepathUnamePword = Paths.get("src/main/java/org/semesteroppgave/loginFiles", "userUsernameAndPassword");
        try {
            Files.write(Paths.get(String.valueOf(filepathUnamePword)), parseUnamePwordTxtToFile().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void parseExistingAdmins() {
        adminList.clear();
        var filepathAInfo = Paths.get("src/main/java/org/semesteroppgave/loginFiles", "adminInfo");
        var filepathAUnamePword = Paths.get("src/main/java/org/semesteroppgave/loginFiles", "adminUsernameAndPassword");

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
                adminList.add(newAdmin);
            }
            parseSaveToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
