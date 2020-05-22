package org.semesteroppgave.models.signin.admin;

import org.semesteroppgave.Main;
import org.semesteroppgave.models.exceptions.InvalidEmployeeNoException;
import org.semesteroppgave.models.exceptions.InvalidUsernameException;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Model for login admin
 */

public class AdminSignIn {

    private final ArrayList<Admin> adminList = new ArrayList<>();
    //Creates a list of valid and vacant employee numbers
    private final ArrayList<String> availableEmpNos = new ArrayList<>();
    private static String activeAdminId;

    public ArrayList<Admin> getAdminList() {
        return this.adminList;
    }

    public void setAdminList(Admin admin) {
        adminList.add(admin);
    }

    public static String getActiveAdminId() {
        return activeAdminId;
    }

    public static void setActiveAdminId(String adminId) {
        activeAdminId = adminId;
    }

    public ArrayList<String> getAvailableEmpNos() {
        return availableEmpNos;
    }

    public void initializeEmpNos() {
        availableEmpNos.clear();
        availableEmpNos.add("A0123");
        availableEmpNos.add("A1234");
        availableEmpNos.add("A2345");
        availableEmpNos.add("A3456");
        availableEmpNos.add("A4567");
        availableEmpNos.add("A5678");
        availableEmpNos.add("A6789");
        availableEmpNos.add("A7890");
        availableEmpNos.add("A8901");
        availableEmpNos.add("A9012");
    }

    public void register(String username, String password, String empNo) throws IOException, IllegalArgumentException {
        checkIfNotExisting(username);
        testValidEmpNo(empNo);
        Admin newAdmin = new Admin(username, password, empNo);
        setAdminList(newAdmin);
        Dialogs.showSuccessDialog("New admin", "New admin registered",
                "Login with username and password");
        saveToFileUsernamePassword();
        saveToFileInfo();
        getAvailableEmpNos().remove(empNo);
        Main.setRoot("adminsignin");
    }

    private void testValidEmpNo(String empNo) {
        for (String admin : availableEmpNos) {
            if (admin.matches(empNo)) {
                return;
            }
        }
        throw new InvalidEmployeeNoException("The employee number is not valid");
    }

    public void parseExistingAdmins() {
        adminList.clear();

        String filepathAInfo = getClass().getResource("/org/semesteroppgave/files/signin/adminInfo.txt").getFile();
        String filepathAUnamePword = getClass().getResource("/org/semesteroppgave/files/signin/adminUsernameAndPassword.txt").getFile();

        try {
            Scanner info = new Scanner(new File(filepathAInfo));
            Scanner unamePword = new Scanner(new File(filepathAUnamePword));

            info.useDelimiter("[\n]");
            unamePword.useDelimiter("[,\n]");

            while (info.hasNext() && unamePword.hasNext()) {
                String empNo = info.next();
                String username = unamePword.next();
                String password = unamePword.next();
                Admin newAdmin = new Admin(username, password, empNo);
                adminList.add(newAdmin);
                availableEmpNos.remove(empNo);
            }
        } catch (IllegalArgumentException | FileNotFoundException e) {
            Dialogs.showErrorDialog("Oups", "An error occurred while parsing admin file", e.getMessage());
        }
    }

    public boolean verifyLogin(String username, String password) {

        String filepath = getClass().getResource("/org/semesteroppgave/files/signin/adminUsernameAndPassword.txt").getFile();

        try {
            Scanner s = new Scanner(new File(filepath));

            s.useDelimiter("[,\n]");
            while (s.hasNext()) {
                String checkUsername = s.next();
                String checkPassword = s.next();

                if (checkUsername.trim().equals(username.trim()) && checkPassword.trim().equals(password.trim())) {
                    System.out.println("\nSuccessful match with username " + username.trim()
                            + " in file adminUsernameAndPassword.txt");
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.print("Error :" + e.getMessage());
        }
        return false;
    }

    private void checkIfNotExisting(String username) {

        String filepath = getClass().getResource("/org/semesteroppgave/files/signin/adminUsernameAndPassword.txt").getFile();

        try {
            Scanner s = new Scanner(new File(filepath));

            s.useDelimiter("[,\n]");
            while (s.hasNext()) {
                String checkUsername = s.next();

                if (checkUsername.trim().equals(username.trim())) {
                    throw new InvalidUsernameException("\nThe username " + username.trim() + " is busy\nChoose a new one");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.print("Error :" + e.getMessage());
        }
    }

    //Saves admin's username and password to file in target directory. Disappears with maven clean
    private void saveToFileUsernamePassword() {
        String filepath = getClass().getResource("/org/semesteroppgave/files/signin/adminUsernameAndPassword.txt").getFile();
        File file = new File(filepath);
        try {
            Files.write(file.toPath(), txtToFileAdminUsernamePassword().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Stores admins info to file in target directory. Disappears with maven clean
    private void saveToFileInfo() {
        String filepath = getClass().getResource("/org/semesteroppgave/files/signin/adminInfo.txt").getFile();
        File file = new File(filepath);
        try {
            Files.write(file.toPath(), txtToFileAdminInfo().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Writes admin's username and password to file
    private String txtToFileAdminUsernamePassword() {
        StringBuilder msgOut = new StringBuilder();
        for (Admin newAdmin : adminList) {
            msgOut.append(newAdmin.printTxtAdminUsernamePassword()).append("\n");
        }
        return msgOut.toString();
    }

    //Writes admin's employee number to file
    private String txtToFileAdminInfo() {
        StringBuilder ut = new StringBuilder();
        for (Admin newAdmin : adminList) {
            ut.append(newAdmin.getEmployeeId()).append("\n");
        }
        return ut.toString();
    }
}
