package org.semesteroppgave.models.exceptions;

public class InvalidComponentException extends IllegalArgumentException {
    public InvalidComponentException(String msg){
        super(msg);
    }
}

