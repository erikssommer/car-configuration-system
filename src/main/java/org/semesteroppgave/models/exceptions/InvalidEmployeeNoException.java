package org.semesteroppgave.models.exceptions;

public class InvalidEmployeeNoException extends IllegalArgumentException {
    public InvalidEmployeeNoException(String msg) {
        super(msg);
    }
}
