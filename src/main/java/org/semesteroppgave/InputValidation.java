package org.semesteroppgave;

import org.semesteroppgave.exceptions.InvalidComponentException;
import org.semesteroppgave.exceptions.InvalidVersionException;
import org.semesteroppgave.gui.Dialogs;

public class InputValidation {

    public static String testValidVersion(String input){
        //TODO Denne kan nok forbedres, men ok for nå
        String[] regex = {
                "[A-ZÆØÅ][0-9]+",
                "[A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå][0-9]+",
        };

        for(String str : regex){
            if(input.matches(str)){
                return input;
            }
        }
        throw new InvalidVersionException("Versjonen du skrev inn er ikke gyldig");
    }

    public static String testValidComponent(String input){
        if (!input.equals("Motor") && !input.equals("Felger") && !input.equals("Setetrekk") && !input.equals("Dekk")
        && !input.equals("Spoiler") && !input.equals("Girboks") && !input.equals("Batteri") && !input.equals("Tank")){
            throw new InvalidComponentException("Det er ikke mulig å opprette en: " + input);
        }
        return input;
    }

    public static class DoubleStringConverter extends javafx.util.converter.DoubleStringConverter {
        private boolean conversionSuccessful;

        @Override
        public Double fromString(String s) {
            try {
                Double result = super.fromString(s);
                conversionSuccessful = true;
                return result;
            } catch(NumberFormatException e) {
                Dialogs.showErrorDialog("Feil","Feil i pris","Du må skrive inn et gyldig tall");
                conversionSuccessful = false;
                return 0.0;
            }
        }

        public boolean wasSuccessful() {
            return conversionSuccessful;
        }
    }
}
