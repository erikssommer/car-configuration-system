package org.semesteroppgave.exceptions;

public class InvalidDeleteException extends IllegalArgumentException{
    public InvalidDeleteException(String msg){
        super(msg);
    }
}
