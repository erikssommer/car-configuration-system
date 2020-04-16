package org.semesteroppgave;

import javafx.scene.control.TableView;
import org.semesteroppgave.carcomponents.Component;
import org.semesteroppgave.exceptions.InvalidComponentException;
import org.semesteroppgave.exceptions.InvalidDeleteException;
import org.semesteroppgave.exceptions.InvalidDescriptionException;
import org.semesteroppgave.exceptions.InvalidVersionException;
import org.semesteroppgave.gui.Dialogs;

public class InputValidation {

    public static String testValidVersion(String input){

        String[] regex = {
                "^[A-ZÆØÅ0-9]+([A-ZÆØÅa-zæøå0-9()[-],. ]){1,15}$"
        };

        for(String str : regex){
            if(input.matches(str)){
                return input;
            }
        }
        throw new InvalidVersionException("Versjonen du skrev inn er ikke gyldig");
    }

    public static String testValidDescription(String input){

        String[] regex = {
                "^[A-ZÆØÅ]+([A-ZÆØÅa-zæøå0-9()[-],. ]){5,50}$",
        };

        for(String str : regex){
            if(input.matches(str)){
                return input;
            }
        }
        throw new InvalidDescriptionException("Beskrivelsen du skrev inn er ikke gyldig");

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
