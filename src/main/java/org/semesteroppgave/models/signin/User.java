package org.semesteroppgave.models.signin;

import org.semesteroppgave.models.exceptions.InvalidEmailException;
import org.semesteroppgave.models.exceptions.InvalidNameException;
import org.semesteroppgave.models.exceptions.InvalidPhonenumberException;

public class User extends Person {

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

    public void setName(String name) throws InvalidNameException {
        this.name = PersonValidator.testValidName(name);
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) throws InvalidPhonenumberException {
        this.phonenumber = PersonValidator.testValidPhone(phonenumber);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailException {
        this.email = PersonValidator.testValidEmail(email);
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

