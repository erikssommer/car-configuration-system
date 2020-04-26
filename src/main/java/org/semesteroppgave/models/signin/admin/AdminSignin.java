package org.semesteroppgave.models.signin.admin;

import org.semesteroppgave.Main;
import org.semesteroppgave.models.exceptions.InvalidEmployeeNoException;
import org.semesteroppgave.models.exceptions.InvalidUsernameException;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminSignin {

    private final ArrayList<Admin> adminList = new ArrayList<>();
    // Oppretter liste med gyldige og ledige ansattnummer
    private final ArrayList<String> availableEmpNos = new ArrayList<>();
    //Aktiv admin inlogg
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
        checkIfNotExisting(username); //Kaster et avvik hvis brukernavnet er opptatt
        testValidEmpNo(empNo); //Kaster et avvik hvis ansattnummeret ikke er gyldig
        Admin newAdmin = new Admin(username, password, empNo);
        setAdminList(newAdmin);
        Dialogs.showSuccessDialog("Ny admin", "Ny admin ble registrert", "Logg inn med brukernavn og passord");
        saveToFileUsernamePassword();
        saveToFileInfo();
        getAvailableEmpNos().remove(empNo);
        Main.setRoot("adminsignin");
    }

    // Sjekker om ansattnummer er gyldig
    private void testValidEmpNo(String empNo) {
        for (String admin : availableEmpNos) {
            if (admin.matches(empNo)) {
                return;
            }
        }
        throw new InvalidEmployeeNoException("Ansattnummeret er ikke gyldig");
    }

    public void parseExistingAdmins() {
        adminList.clear();
        var filepathAInfo = Paths.get("src/main/resources/org/semesteroppgave/files/signin", "adminInfo");
        var filepathAUnamePword = Paths.get("src/main/resources/org/semesteroppgave/files/signin", "adminUsernameAndPassword");

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
                availableEmpNos.remove(empNo);
            }
        } catch (IllegalArgumentException | FileNotFoundException e) {
            Dialogs.showErrorDialog("Oups", "En feil har skjedd ved parsing av adminfil", e.getMessage());
        }
    }

    public boolean verifyLogin(String username, String password) {

        var filepath = Paths.get("src/main/resources/org/semesteroppgave/files/signin", "adminUsernameAndPassword");

        try {
            Scanner s = new Scanner(new File(String.valueOf(filepath)));

            s.useDelimiter("[,\n]");
            while (s.hasNext()) {
                String checkUsername = s.next();
                String checkPassword = s.next();

                if (checkUsername.trim().equals(username.trim()) && checkPassword.trim().equals(password.trim())) {
                    System.out.println("\nSuccessful match with username " + username.trim() + " in file adminUsernameAndPassword");
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.print("No match with username " + username.trim() + " in file adminUsernameAndPassword");
            System.out.print("Error :" + e.getMessage());
        }
        return false;
    }

    private void checkIfNotExisting(String username) {

        var filepath = Paths.get("src/main/resources/org/semesteroppgave/files/signin", "adminUsernameAndPassword");

        try {
            Scanner s = new Scanner(new File(String.valueOf(filepath)));

            s.useDelimiter("[,\n]");
            while (s.hasNext()) {
                String checkUsername = s.next();

                if (checkUsername.trim().equals(username.trim())) {
                    throw new InvalidUsernameException("\nBrukernavnet " + username.trim() + " er opptatt\nVelg et nytt");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.print("Error :" + e.getMessage());
        }
    }

    // Lagrer admins brukernavn og passord til fil
    private void saveToFileUsernamePassword() {
        var filepath = Paths.get("src/main/resources/org/semesteroppgave/files/signin", "adminUsernameAndPassword");
        try {
            Files.write(Paths.get(String.valueOf(filepath)), txtToFileAdminUsernamePassword().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lagrer admins info til fil
    private void saveToFileInfo() {
        var filepath = Paths.get("src/main/resources/org/semesteroppgave/files/signin", "adminInfo");
        try {
            Files.write(Paths.get(String.valueOf(filepath)), txtToFileAdminInfo().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Skriver admins brukernavn og passord til fil
    private String txtToFileAdminUsernamePassword() {
        StringBuilder ut = new StringBuilder();
        for (Admin newAdmin : adminList) {
            ut.append(newAdmin.printTxtAdminUnamePword()).append("\n");
        }
        return ut.toString();
    }

    // Skriver admins ansattnummer til fil
    private String txtToFileAdminInfo() {
        StringBuilder ut = new StringBuilder();
        for (Admin newAdmin : adminList) {
            ut.append(newAdmin.getEmployeeId()).append("\n");
        }
        return ut.toString();
    }
}
