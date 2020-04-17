package org.semesteroppgave.models.exceptions;

public class DuplicateException extends IllegalArgumentException {
    public DuplicateException(String msg){
        super(msg);
    }
}
