package org.semesteroppgave.models.exceptions;

public class InvalidProductException extends IllegalArgumentException {
    public InvalidProductException(String msg) {
        super(msg);
    }
}
