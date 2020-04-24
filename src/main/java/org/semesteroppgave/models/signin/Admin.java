package org.semesteroppgave.models.signin;

import org.semesteroppgave.models.exceptions.InvalidEmployeeNoException;

public class Admin extends Person {

    private final String employeeId;

    public Admin(String username, String password, String employeeId) {
        super(username, password);
        if (employeeId.isEmpty()) throw new InvalidEmployeeNoException("Du må angi adminid");
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    //Format til fil
    public String printTxtAdminUnamePword() {
        return getUsername() + "," + getPassword();
    }
}

