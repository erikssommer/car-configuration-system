package org.semesteroppgave.exceptions;

public class InvalidVersionException extends IllegalArgumentException {
    public InvalidVersionException(String msg){
        super(msg);
    }
}
