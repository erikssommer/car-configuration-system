package org.semesteroppgave.exceptions;

public class InvalidUsernameException extends IllegalArgumentException {
    public InvalidUsernameException(String msg){
        super(msg);
    }
}
