package org.semesteroppgave.exceptions;

public class InvalidPriceException extends IllegalArgumentException {
    public InvalidPriceException(String msg){
        super(msg);
    }
}
