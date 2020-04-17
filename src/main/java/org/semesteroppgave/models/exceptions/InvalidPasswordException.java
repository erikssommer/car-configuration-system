package org.semesteroppgave.models.exceptions;

public class InvalidPasswordException extends IllegalArgumentException {
    public InvalidPasswordException(String msg){
        super(msg);
    }
}

