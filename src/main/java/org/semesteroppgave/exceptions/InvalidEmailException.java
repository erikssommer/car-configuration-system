package org.semesteroppgave.exceptions;

public class InvalidEmailException extends IllegalArgumentException {
    public InvalidEmailException(String msg) {
        super(msg);
    }
}
