package org.semesteroppgave.models.utilities.inputhandler;

import org.semesteroppgave.models.exceptions.*;

/**
 * Class for validating new registered users and admin
 */

public class PersonValidator {

    public static String testValidName(String name) throws InvalidNameException{
        //Supports names that consist of one to four words.
        //The first two names may have a hyphen between the names. ex: Markus-Johannes Pedersen
        String[] regex = {
                "[A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+[-][A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå]+[-][A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]+"
        };

        for (String str : regex) {
            if (name.matches(str)) {
                return name;
            }
        }
        throw new InvalidNameException("The name you entered is not valid\n");
    }

    public static String testValidPhone(String phonenumber) throws InvalidPhonenumberException {
        String[] regex = {
                "[0-9]{8}",
                "[+][4][7][0-9]{8}",
                "[0-9]{3}-[0-9]{4}",
                "[+][4][7] [0-9]{8}",
                "[0][0-9]{4} [0-9]{6}",
                "[0]{2} [4][7] [0-9]{8}",
                "[(][+][4][7][)][0-9]{8}",
                "[(][+][4][7][)] [0-9]{8}",
                "[+][4]{2} [0-9]{4} [0-9]{6}",
                "[(]541[)] [0-9]{3}-[0-9]{4}",
                "[1]-[0-9]{3}-[0-9]{3}-[0-9]{4}",
                "[+][1]-[0-9]{3}-[0-9]{3}-[0-9]{4}",
                "[0]{2}[1]-[0-9]{3}-[0-9]{3}-[0-9]{4}",
                "[0]{2} [4][7] [0-9]{3} [0-9]{2} [0-9]{3}",
                "[(][+][4][7][)] [0-9]{2} [0-9]{2} [0-9]{2} [0-9]{2}",
        };

        for (String str : regex) {
            if (phonenumber.matches(str)) {
                return phonenumber;
            }
        }
        throw new InvalidPhonenumberException("Please enter a valid phone number\n");
    }

    public static String testValidEmail(String email) throws InvalidEmailException {
        String[] regex = {
                "[a-zæøåA-ZÆØÅ0-9.]+[@][a-z]+[.][a-z]+",
                "[a-zæøåA-ZÆØÅ0-9.]+[@][a-z]+[.][a-z]+[.][a-z]+"
        };

        for (String str : regex) {
            if (email.matches(str)) {
                return email;
            }
        }
        throw new InvalidEmailException("Please enter a valid email\n");
    }

    public static String testValidUsername(String username) throws InvalidUsernameException {
        String regex = "(?!^[0-9]*$)(?!^[a-zæøåA-ZÆØÅ]*$)^([a-zæøåA-ZÆØÅ0-9]{6,})$";

        if (username.matches(regex)) {
            return username;
        }
        throw new InvalidUsernameException("Please enter a valid username.\n" +
                "Must be at least 6 characters and contain\nat least one letter and number");
    }

    public static String testValidPassword(String password) throws InvalidPasswordException {
        String regex = "^(?=.*[A-ZÆØÅa-zæøå])(?=.*\\d)[A-ZÆØÅa-zæøå\\d]{4,}$";

        if (password.matches(regex)) {
            return password;
        }
        throw new InvalidPasswordException("Please enter a valid password.\n" +
                "Must be at least 4 characters long and contain\nt least one letter and number");
    }
}
