package org.semesteroppgave.models.signin.admin;

import org.semesteroppgave.models.exceptions.InvalidEmployeeNoException;
import org.semesteroppgave.models.signin.Person;

public class Admin extends Person {

    private final String employeeId;

    public Admin(String username, String password, String employeeId) {
        super(username, password);
        if (employeeId.isEmpty()) throw new InvalidEmployeeNoException("You must enter employee number");
        this.employeeId = employeeId;
    }

    //All getters and NO setters to provide immutability
    public String getEmployeeId() {
        return employeeId;
    }

    //Format for txt-file
    public String printTxtAdminUsernamePassword() {
        return getUsername() + "," + getPassword();
    }
}

