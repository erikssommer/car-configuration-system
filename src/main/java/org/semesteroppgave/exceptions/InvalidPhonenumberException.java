package org.semesteroppgave.exceptions;

public class InvalidPhonenumberException extends IllegalArgumentException {
    public InvalidPhonenumberException(String msg){
        super(msg);
    }
}
