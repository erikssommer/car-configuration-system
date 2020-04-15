package org.semesteroppgave.signin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonLogin {

    private static ArrayList<User> userList = new ArrayList<>();
    private static ArrayList<Admin> adminList = new ArrayList<>();
    private static String activeAdminId;

    public static ArrayList<User> getUserList(){
        return userList;
    }

    public static void setUserList(User user){
        userList.add(user);
    }

    public static ArrayList<Admin> getAdminList(){
        return adminList;
    }

    public static void setAdminList(Admin admin){
        adminList.add(admin);
    }

    public static String getActiveAdminId(){
        return activeAdminId;
    }

    public static void setActiveAdminId(String adminId){
        activeAdminId = adminId;
    }

    public static boolean verifyLogin(String username, String password, String usernameAndPasswordfile){

        var filepath = Paths.get("src/main/java/org/semesteroppgave/signin/loginFiles", usernameAndPasswordfile);

        try{
            Scanner s = new Scanner(new File(String.valueOf(filepath)));

            s.useDelimiter("[,\n]");
            while (s.hasNext()){
                String checkUsername = s.next();
                String checkPassword = s.next();

                if(checkUsername.trim().equals(username.trim()) && checkPassword.trim().equals(password.trim())){
                    System.out.println("\nSuccessful match with username "+username.trim()+" in file "+usernameAndPasswordfile);
                    return true;
                }
            }
        }catch (Exception e){
            System.out.print("No match with username "+username.trim()+" in file "+usernameAndPasswordfile);
            System.out.print("Error :"+e.getMessage());
        }
        return false;
    }

    public static boolean checkIfNotExisting(String username, String usernameAndPasswordfile){

        var filepath = Paths.get("src/main/java/org/semesteroppgave/signin/loginFiles", usernameAndPasswordfile);

        try{
            Scanner s = new Scanner(new File(String.valueOf(filepath)));

            s.useDelimiter("[,\n]");
            while (s.hasNext()){
                String checkUsername = s.next();
                String checkPassword = s.next();


                if(checkUsername.trim().equals(username.trim())){
                    System.out.println("\nUsername "+username.trim()+" already exists in file "+usernameAndPasswordfile);
                    return false;
                }
            }
        }catch (Exception e){
            System.out.print("No match with username "+username.trim()+" in file "+usernameAndPasswordfile);
            System.out.print("Error :"+e.getMessage());
        }
        return true;
    }

    // Lagrer admins brukernavn og passord til fil
    public static void saveToFileUsernamePassword(String file){
        var filepath = Paths.get("src/main/java/org/semesteroppgave/signin/loginFiles", file);
        try {
            if (file.equals("adminUsernameAndPassword")){
                Files.write(Paths.get(String.valueOf(filepath)), txtToFileAdminUsernamePassword().getBytes());
            }else {
                Files.write(Paths.get(String.valueOf(filepath)), txtToFileUsernamePassword().getBytes());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // Lagrer admins info til fil
    public static void saveToFileInfo(String file){
        var filepath = Paths.get("src/main/java/org/semesteroppgave/signin/loginFiles", file);
        try {
            if (file.equals("adminInfo")){
                Files.write(Paths.get(String.valueOf(filepath)), txtToFileAdminInfo().getBytes());
            }else {
                Files.write(Paths.get(String.valueOf(filepath)), txtToFileUserInfo().getBytes());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Skriver brukers brukernavn og passord til fil
    public static String txtToFileUsernamePassword() {
        StringBuilder ut = new StringBuilder();
        for (User newUser : userList) {
            ut.append(newUser.printTxtUsernamePassword()).append("\n");
        }
        return ut.toString();
    }
    // Skriver brukers info til fil
    public static String txtToFileUserInfo() {
        StringBuilder ut = new StringBuilder();
        for (User newUser : userList) {
            ut.append(newUser.printTxtUserInfo()).append("\n");
        }
        return ut.toString();
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
}
