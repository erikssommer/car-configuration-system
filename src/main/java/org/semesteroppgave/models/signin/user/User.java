package org.semesteroppgave.models.signin.user;

import org.semesteroppgave.models.exceptions.InvalidNameException;
import org.semesteroppgave.models.signin.Person;
import org.semesteroppgave.models.utilities.inputhandler.PersonValidator;

public class User extends Person {

    private final String name;
    private final String email;
    private final String phonenumber;

    public User(String username, String password, String name, String phonenumber, String email) {
        super(username, password);
        if (name.isEmpty()) throw new InvalidNameException("Du må fylle inn navnet");
        if (email.isEmpty()) throw new InvalidNameException("Du må fylle inn email");
        if (phonenumber.isEmpty()) throw new InvalidNameException("Du må fylle inn telefonnummeret");
        this.name = PersonValidator.testValidName(name);
        this.email = PersonValidator.testValidEmail(email);
        this.phonenumber = PersonValidator.testValidPhone(phonenumber);
    }

    //All getters and NO setters to provide immutability
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    //Format for txt file
    public String printTxtUsernamePassword() {
        return getUsername() + "," + getPassword();
    }

    public String printTxtUserInfo() {
        return getName() + "," + getPhonenumber() + "," + getEmail();
    }

}

