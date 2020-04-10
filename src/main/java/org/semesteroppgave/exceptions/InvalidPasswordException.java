package org.semesteroppgave.exceptions;

public class InvalidPasswordException extends IllegalArgumentException {
    public InvalidPasswordException(String msg){
        super(msg);
    }
}

