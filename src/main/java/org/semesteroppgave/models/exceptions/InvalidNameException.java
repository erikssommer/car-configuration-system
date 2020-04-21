package org.semesteroppgave.models.exceptions;

public class InvalidNameException extends IllegalArgumentException {
    public InvalidNameException(String msg) {
        super(msg);
    }
}
