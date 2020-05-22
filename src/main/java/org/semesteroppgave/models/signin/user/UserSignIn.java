package org.semesteroppgave.models.signin.user;

import org.semesteroppgave.Main;
import org.semesteroppgave.models.exceptions.InvalidUsernameException;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Login User Model
 */

public class UserSignIn {
    private final ArrayList<User> userList = new ArrayList<>();
    private static String activeUsername;

    public ArrayList<User> getUserList() {
        return this.userList;
    }

    public void setUserList(User user) {
        userList.add(user);
    }

    public static String getActiveUsername() {
        return activeUsername;
    }

    public static void setActiveUsername(String activeUsername) {
        UserSignIn.activeUsername = activeUsername;
    }

    public void register(String username, String password, String name, String phonenumber, String eMail) throws IOException, IllegalArgumentException {
        checkIfNotExisting(username);
        User newUser = new User(username, password, name, phonenumber, eMail);
        setUserList(newUser);
        Dialogs.showSuccessDialog("New user", "New user was registered",
                "Login with username and password");
        saveToFileUsernamePassword();
        saveToFileInfo();
        Main.setRoot("usersignin");
    }

    //Loads existing users from user files and places them in an ArrayList.
    public void parseExistingUser() {
        userList.clear();
        String filepathUnamePword = getClass().getResource("/org/semesteroppgave/files/signin/userUsernameAndPassword.txt").getFile();
        String filepathUInfo = getClass().getResource("/org/semesteroppgave/files/signin/userInfo.txt").getFile();

        try {
            Scanner s = new Scanner(new File(filepathUInfo));
            Scanner g = new Scanner(new File(filepathUnamePword));

            s.useDelimiter("[,\n]");
            g.useDelimiter("[,\n]");

            while (s.hasNext() && g.hasNext()) {
                String name = s.next();
                String phonenumber = s.next();
                String email = s.next();
                String username = g.next();
                String password = g.next();
                User newUser = new User(username, password, name, phonenumber, email);
                userList.add(newUser);
            }
        } catch (IllegalArgumentException | FileNotFoundException e) {
            Dialogs.showErrorDialog("Oups", "An error occurred while parsing user file", e.getMessage());
        }
    }

    public boolean verifyLogin(String username, String password) {

        String filepath = getClass().getResource("/org/semesteroppgave/files/signin/userUsernameAndPassword.txt").getFile();

        try {
            Scanner s = new Scanner(new File(filepath));

            s.useDelimiter("[,\n]");
            while (s.hasNext()) {
                String checkUsername = s.next();
                String checkPassword = s.next();

                if (checkUsername.trim().equals(username.trim()) && checkPassword.trim().equals(password.trim())) {
                    System.out.println("\nSuccessful match with username " + username.trim() + " in file userUsernameAndPassword.txt");
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.print("Error :" + e.getMessage());
        }
        return false;
    }

    private void checkIfNotExisting(String username) throws InvalidUsernameException {

        String filepath = getClass().getResource("/org/semesteroppgave/files/signin/userUsernameAndPassword.txt").getFile();

        try {
            Scanner s = new Scanner(new File(filepath));

            s.useDelimiter("[,\n]");
            while (s.hasNext()) {
                String checkUsername = s.next();

                if (checkUsername.trim().equals(username.trim())) {
                    throw new InvalidUsernameException("\nThe username " + username.trim() + " is not available\nChoose a new one");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.print("Error :" + e.getMessage());
        }
    }

    //Saves admin's username and password to file in target directory. Disappears with maven clean
    private void saveToFileUsernamePassword() {
        String filepath = getClass().getResource("/org/semesteroppgave/files/signin/userUsernameAndPassword.txt").getFile();
        File file = new File(filepath);
        try {
            Files.write(file.toPath(), txtToFileUsernamePassword().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Stores admins info to file in target directory. Disappears with maven clean
    private void saveToFileInfo() {
        String filepath = getClass().getResource("/org/semesteroppgave/files/signin/userInfo.txt").getFile();
        File file = new File(filepath);
        try {
            Files.write(file.toPath(), txtToFileUserInfo().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Writes user's user name and password to file
    private String txtToFileUsernamePassword() {
        StringBuilder msgOut = new StringBuilder();
        for (User newUser : userList) {
            msgOut.append(newUser.printTxtUsernamePassword()).append("\n");
        }
        return msgOut.toString();
    }

    //Writes user info to file
    private String txtToFileUserInfo() {
        StringBuilder msgOut = new StringBuilder();
        for (User newUser : userList) {
            msgOut.append(newUser.printTxtUserInfo()).append("\n");
        }
        return msgOut.toString();
    }
}
