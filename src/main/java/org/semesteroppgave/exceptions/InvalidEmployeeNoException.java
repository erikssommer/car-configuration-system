package org.semesteroppgave.exceptions;

public class InvalidEmployeeNoException extends IllegalArgumentException {
    public InvalidEmployeeNoException(String msg){
        super(msg);
    }
}
