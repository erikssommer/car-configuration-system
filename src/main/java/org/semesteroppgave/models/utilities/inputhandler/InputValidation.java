package org.semesteroppgave.models.utilities.inputhandler;

import javafx.scene.control.TableView;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.components.Component;
import org.semesteroppgave.models.exceptions.*;

/**
 * Klasse for validering av input ved opprettelse/redigering av Component-objekt
 */

public class InputValidation {

    public static String testValidVersion(String input) {

        String[] regex = {
                "^[A-ZÆØÅ]+([A-ZÆØÅa-zæøå0-9()[-],. ]){1,25}$",
                "^[0-9]+[[-]a-zæøå A-ZÆØÅ().,]{1,25}$"
        };

        for (String str : regex) {
            if (input.matches(str)) {
                return input;
            }
        }

        throw new InvalidVersionException("Versjonen du skrev inn er ikke gyldig" +
                "\nDen må være mellom 2 og 25 tegn\nog starte med stor bokstav.");
    }

    public static String testValidDescription(String input) {

        String regex = "[A-ZÆØÅ]+([A-ZÆØÅa-zæøå0-9()[-],. ]){4,100}$";

        if (input.matches(regex)) {
            return input;
        }

        throw new InvalidDescriptionException("Beskrivelsen du skrev inn er ikke gyldig." +
                "\nDen må være mellom 5 og 100 tegn\nog starte med stor bokstav.");

    }

    public static String testValidComponent(String input) {
        if (!input.toLowerCase().equals("motor") && !input.toLowerCase().equals("felg")
                && !input.toLowerCase().equals("setetrekk") && !input.toLowerCase().equals("dekk")
                && !input.toLowerCase().equals("spoiler") && !input.toLowerCase().equals("girboks")
                && !input.toLowerCase().equals("batteri") && !input.toLowerCase().equals("tank")) {
            throw new InvalidComponentException("Det er ikke mulig å opprette en: " + input);
        }
        return input;
    }

    public static double testValidPrice(double input) {
        String price = String.valueOf(input);
        String regex = "^[0-9]+(.|,)?[0-9]{1,3}$";
        if (price.matches(regex)){
            return input;
        }
        throw new InvalidPriceException("Prisen kan ikke være negativ eller ha flere enn 3 desimaler");
    }

    public static void testComponentCount(TableView<Component> tableViewComponents, String input) {
        int counter = 0;
        for (Component component : ApplicationData.getInstance().getRegisterComponent().getComponentList()) {
            if (component.getComponent().equals(tableViewComponents.getSelectionModel().getSelectedItem().getComponent())) {
                counter++;
            }
        }
        //Hvis denne intreffer er det bare én av denne type komponent igjen og vi kaster et avvik
        if (counter == 1) {
            tableViewComponents.refresh();
            throw new InvalidDeleteException("Kan ikke " + input + " denne komponenten " +
                    "fordi det må minst være én av denne komponenten. Hvis det manger en komponent når brukeren" +
                    " oppretter et produkt, vil brukeren ikke ha mulighet til å opprette et produkt");
        }
    }
}
