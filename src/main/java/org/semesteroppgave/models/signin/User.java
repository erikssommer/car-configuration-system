package org.semesteroppgave.models.signin;

import org.semesteroppgave.models.exceptions.InvalidNameException;

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

    // Alle getter og INGEN setter for å gi uforanderlighet
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    //Format til fil
    public String printTxtUsernamePassword() {
        return getUsername() + "," + getPassword();
    }

    //Format til fil
    public String printTxtUserInfo() {
        return getName() + "," + getPhonenumber() + "," + getEmail();
    }

}

