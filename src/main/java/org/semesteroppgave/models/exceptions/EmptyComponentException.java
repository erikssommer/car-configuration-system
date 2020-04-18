package org.semesteroppgave.models.exceptions;

public class EmptyComponentException extends IllegalArgumentException {
    public EmptyComponentException(String msg){
        super(msg);
    }
}
