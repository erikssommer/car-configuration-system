package org.semesteroppgave.models.signin;

import org.semesteroppgave.models.signin.*;
import org.semesteroppgave.models.exceptions.InvalidUsernameException;

abstract class Person {

    private String username;
    private String password;

    public Person(String username, String password){
        PersonValidator.testValidUsername(username);
        PersonValidator.testValidPassword(password);
        if (username.isEmpty()) throw new InvalidUsernameException("Du må fylle inn brukernavn");
        if (password.isEmpty()) throw new InvalidUsernameException("Du må fylle inn passord");
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = PersonValidator.testValidUsername(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PersonValidator.testValidPassword(password);
    }
}






