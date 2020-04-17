package org.semesteroppgave.models.exceptions;

public class InvalidDeleteException extends IllegalArgumentException{
    public InvalidDeleteException(String msg){
        super(msg);
    }
}
