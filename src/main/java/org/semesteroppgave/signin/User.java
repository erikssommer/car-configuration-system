package org.semesteroppgave.signin;

import org.semesteroppgave.exceptions.InvalidEmailException;
import org.semesteroppgave.exceptions.InvalidNameException;
import org.semesteroppgave.exceptions.InvalidPhonenumberException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class User extends Person {

    public static ArrayList<User> userList = new ArrayList<>();

    private String name;
    private String phonenumber;
    private String email;

    public User(String username, String password, String name, String phonenumber, String email) {
        super(username, password);
        if (name.isEmpty()) throw new InvalidNameException("Du må fylle inn navnet");
        if (phonenumber.isEmpty()) throw new InvalidNameException("Du må fylle inn telefonnummeret");
        if (email.isEmpty()) throw new InvalidNameException("Du må fylle inn email");
        this.name = name;
        this.phonenumber = phonenumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidNameException{
        this.name = PersonValidator.testValidName(name);
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) throws InvalidPhonenumberException{
        this.phonenumber = PersonValidator.testValidPhone(phonenumber);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailException {
        this.email = PersonValidator.testValidEmail(email);
    }

    public String printTxtUsernamePassword() {
        return getUsername() + "," + getPassword();
    }

    public String printTxtUserInfo() {
        return getName() + "," + getPhonenumber()+ "," +getEmail();
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
    // Lagrer brukers brukernavn og passord til fil
    public static void saveToFileUsernamePassword(){
        var filepath = Paths.get("src/main/java/org/semesteroppgave/signin/loginFiles", "userUsernameAndPassword");
        try {
            Files.write(Paths.get(String.valueOf(filepath)), txtToFileUsernamePassword().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // Lagrer brukers info til fil
    public static void saveToFileUserInfo(){
        var filepath = Paths.get("src/main/java/org/semesteroppgave/signin/loginFiles", "userInfo");
        try {
            Files.write(Paths.get(String.valueOf(filepath)), txtToFileUserInfo().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // Legger innlest bruker til ArrayList
    public static void registerUser(User user) throws IOException {
        userList.add(user);
    }
}

