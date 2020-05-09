package org.semesteroppgave.models.signin.admin;

import org.semesteroppgave.models.exceptions.InvalidEmployeeNoException;
import org.semesteroppgave.models.signin.Person;

public class Admin extends Person {

    private final String employeeId;

    public Admin(String username, String password, String employeeId) {
        super(username, password);
        if (employeeId.isEmpty()) throw new InvalidEmployeeNoException("Du må angi adminid");
        this.employeeId = employeeId;
    }

    // Alle getter og INGEN setter for å gi uforanderlighet
    public String getEmployeeId() {
        return employeeId;
    }

    //Format til fil
    public String printTxtAdminUsernamePassword() {
        return getUsername() + "," + getPassword();
    }
}

