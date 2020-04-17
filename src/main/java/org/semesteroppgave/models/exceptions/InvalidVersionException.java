package org.semesteroppgave.models.exceptions;

public class InvalidVersionException extends IllegalArgumentException {
    public InvalidVersionException(String msg){
        super(msg);
    }
}
