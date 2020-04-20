package org.semesteroppgave.models.utilities.inputhandler;

import javafx.scene.control.TableView;
import org.semesteroppgave.Context;
import org.semesteroppgave.models.data.carcomponents.Component;
import org.semesteroppgave.models.exceptions.*;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

public class InputValidation {

    public static String testValidVersion(String input){

        String[] regex = {
                "^[A-ZÆØÅ]+([A-ZÆØÅa-zæøå0-9()[-],. ]){1,25}$",
                "^[0-9]+[[-]a-zæøå A-ZÆØÅ().,]{1,25}$"
        };

        // ^[A-ZÆØÅ0-9]+([A-ZÆØÅa-zæøå0-9()[-],. ]){1,25}$

        for(String str : regex){
            if(input.matches(str)){
                return input;
            }
        }

        throw new InvalidVersionException("Versjonen du skrev inn er ikke gyldig");
    }

    public static String testValidDescription(String input){

        String regex = "[A-ZÆØÅ]+([A-ZÆØÅa-zæøå0-9()[-],. ]){5,100}$";

        if(input.matches(regex)){
            return input;
        }

        throw new InvalidDescriptionException("Beskrivelsen du skrev inn er ikke gyldig.\nDen må være mellom 5 og 100 tegn.");

    }

    public static String testValidComponent(String input){
        if (!input.equals("Motor") && !input.equals("Felg") && !input.equals("Setetrekk") && !input.equals("Dekk")
        && !input.equals("Spoiler") && !input.equals("Girboks") && !input.equals("Batteri") && !input.equals("Tank")){
            throw new InvalidComponentException("Det er ikke mulig å opprette en: " + input);
        }
        return input;
    }

    public static double testValidPrice(double input) {
        DoubleStringConverter doubleStrConverter = new DoubleStringConverter();
        doubleStrConverter.fromString(String.valueOf(input));
        if (doubleStrConverter.wasSuccessful()){
            return input;
        }
        throw new InvalidPriceException("Prisen er ugyldig");
    }

    public static class DoubleStringConverter extends javafx.util.converter.DoubleStringConverter {
        private boolean conversionSuccessful;

        @Override
        public Double fromString(String s) {
            try {
                Double result = super.fromString(s);
                if (result < 0.0){
                    throw new InvalidPriceException("Prisen kan ikke være negativ");
                }
                conversionSuccessful = true;
                return result;
            } catch(NumberFormatException e) {
                Dialogs.showErrorDialog("Feil","Feil i pris","Du må skrive inn et gyldig tall");
                conversionSuccessful = false;
                return 0.0;
            } catch (NullPointerException e){
                Dialogs.showErrorDialog("Feil","Feil i pris", "Du må fylle inn prisen");
                conversionSuccessful = false;
                return 0.0;
            } catch (InvalidPriceException e){
                Dialogs.showErrorDialog("Feil","Feil i pris",e.getMessage());
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
