package org.semesteroppgave.models.exceptions;

public class InvalidUsernameException extends IllegalArgumentException {
    public InvalidUsernameException(String msg){
        super(msg);
    }
}
