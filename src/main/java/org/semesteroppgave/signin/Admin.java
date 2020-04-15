package org.semesteroppgave.signin;

import org.semesteroppgave.exceptions.InvalidEmployeeNoException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Admin extends Person {

    private String employeeId;

    public Admin(String username, String password, String employeeId) {
        super(username, password);
        if (employeeId.isEmpty()) throw new InvalidEmployeeNoException("Du må angi adminid");
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) throws InvalidEmployeeNoException {
        this.employeeId = employeeId;
    }

    //Format til fil
    public String printTxtAdminUnamePword(){
        return getUsername()+","+getPassword();
    }
}

