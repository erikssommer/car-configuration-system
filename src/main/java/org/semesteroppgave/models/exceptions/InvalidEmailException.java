package org.semesteroppgave.models.exceptions;

public class InvalidEmailException extends IllegalArgumentException {
    public InvalidEmailException(String msg) {
        super(msg);
    }
}
