package org.semesteroppgave.models.exceptions;

public class InvalidModelException extends IllegalArgumentException {
    public InvalidModelException(String msg) {
        super(msg);
    }
}
