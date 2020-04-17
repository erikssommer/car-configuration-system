package org.semesteroppgave.models.exceptions;

public class InvalidPhonenumberException extends IllegalArgumentException {
    public InvalidPhonenumberException(String msg){
        super(msg);
    }
}
