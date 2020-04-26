package org.semesteroppgave.models.signin;

import org.semesteroppgave.models.exceptions.InvalidUsernameException;
import org.semesteroppgave.models.utilities.inputhandler.PersonValidator;

/**
 * Abstrakt klasse for alle personer (admin og bruker)
 */

public abstract class Person {

    private final String username;
    private final String password;

    public Person(String username, String password) {
        if (username.isEmpty()) throw new InvalidUsernameException("Du må fylle inn brukernavn");
        if (password.isEmpty()) throw new InvalidUsernameException("Du må fylle inn passord");
        this.username = PersonValidator.testValidUsername(username);
        this.password = PersonValidator.testValidPassword(password);
    }

    // Alle getter og INGEN setter for å gi uforanderlighet
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}






