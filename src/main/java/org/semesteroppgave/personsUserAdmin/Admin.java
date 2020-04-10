package org.semesteroppgave.personsUserAdmin;

import org.semesteroppgave.exceptions.InvalidEmployeeNoException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Admin extends Person {

    public static ArrayList<Admin> adminList = new ArrayList<>();
    private String employeeId;

    public Admin(String username, String password, String employeeId) {
        super(username, password);
        if (employeeId.isEmpty()) throw new InvalidEmployeeNoException("Du må fylle inn adminid");
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) throws InvalidEmployeeNoException {
        this.employeeId = employeeId;
    }

    public String printTxtAdminUnamePword(){
        return getUsername()+","+getPassword();
    }
    // Skriver admins brukernavn og passord til fil
    public static String txtToFileAdminUsernamePassword() {
        StringBuilder ut = new StringBuilder();
        for (Admin newAdmin : adminList) {
            ut.append(newAdmin.printTxtAdminUnamePword()).append("\n");
        }
        return ut.toString();
    }

    // Skriver admins ansattnummer til fil
    public static String txtToFileAdminInfo() {
        StringBuilder ut = new StringBuilder();
        for (Admin newAdmin : adminList) {
            ut.append(newAdmin.getEmployeeId()).append("\n");
        }
        return ut.toString();
    }
    // Lagrer admins brukernavn og passord til fil
    public static void saveToFileUsernamePassword(){
        var filepath = Paths.get("src/main/java/org/semesteroppgave/loginFiles", "adminUsernameAndPassword");
        try {
            Files.write(Paths.get(String.valueOf(filepath)), txtToFileAdminUsernamePassword().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // Lagrer admins info til fil
    public static void saveToFileAdminInfo(){
        var filepath = Paths.get("src/main/java/org/semesteroppgave/loginFiles", "adminInfo");
        try {
            Files.write(Paths.get(String.valueOf(filepath)), txtToFileAdminInfo().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void registerAdmin(Admin admin){
        adminList.add(admin);
    }


}

