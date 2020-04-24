package org.semesteroppgave.models.signin;

import org.semesteroppgave.models.exceptions.InvalidNameException;

public class User extends Person {

    private final String name;
    private final String phonenumber;
    private final String email;

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

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
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

