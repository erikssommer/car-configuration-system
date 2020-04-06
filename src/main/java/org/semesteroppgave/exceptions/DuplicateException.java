package org.semesteroppgave.exceptions;

public class DuplicateException extends IllegalArgumentException {
    public DuplicateException(String msg){
        super(msg);
    }
}
