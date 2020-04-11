package org.semesteroppgave;

import javafx.scene.control.TableView;
import org.semesteroppgave.carcomponents.Component;
import org.semesteroppgave.exceptions.InvalidComponentException;
import org.semesteroppgave.exceptions.InvalidDeleteException;
import org.semesteroppgave.gui.Dialogs;

public class InputValidation {

    public static String testValidVersion(String input){
        //TODO Denne kan nok forbedres
        /*
        String[] regex = {
                "[A-ZÆØÅ][0-9]+",
                "[A-ZÆØÅ][-][a-zæøå]+",
                "[A-ZÆØÅ][-][0-9]+",
                "[A-ZÆØÅ][a-zæøå]+",
                "[A-ZÆØÅ][-][a-zæøå]+",
                "[A-ZÆØÅ][a-zæøå][0-9]+",
                "[A-ZÆØÅ][-][a-zæøå][0-9]+",
        };

        for(String str : regex){
            if(input.matches(str)){
                return input;
            }
        }
        throw new InvalidVersionException("Versjonen du skrev inn er ikke gyldig");

         */
        return input;
    }

    public static String testValidDescription(String input){
        //TODO finne en enkel og grei string regex for beskrivelsen
        /*
        String[] regex = {
                "^[A-ZÆØÅa-zæøå,!-][0-9]++$",
        };

        for(String str : regex){
            if(input.matches(str)){
                return input;
            }
        }
        throw new InvalidDescriptionException("Beskrivelsen du skrev inn er ikke gyldig");

         */
        return input;
    }

    public static String testValidComponent(String input){
        if (!input.equals("Motor") && !input.equals("Felg") && !input.equals("Setetrekk") && !input.equals("Dekk")
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

    public static void testComponentCount(TableView<Component> tableViewComponents, String input){
        int counter = 0;
        for (Component component : Context.getInstance().getRegisterComponent().getComponentsList()){
            if (component.getComponent().equals(tableViewComponents.getSelectionModel().getSelectedItem().getComponent())){
                counter++;
            }
        }
        //Hvis denne intreffer er det bare én av denne type komponent igjen og vi kaster et avvik
        if (counter == 1){
            tableViewComponents.refresh();
            throw new InvalidDeleteException("Kan ikke "+ input + " denne komponenten " +
                    "fordi det må minst være én av denne komponenten. Hvis det manger en komponent når brukeren" +
                    " oppretter en bil, vil brukeren ikke ha mulighet til å opprette en bil");
        }
    }
}
