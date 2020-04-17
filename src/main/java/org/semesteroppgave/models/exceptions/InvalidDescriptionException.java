package org.semesteroppgave.models.exceptions;

public class InvalidDescriptionException extends IllegalArgumentException{
    public InvalidDescriptionException(String msg){
        super(msg);
    }
}
