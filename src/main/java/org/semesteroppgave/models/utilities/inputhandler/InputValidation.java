package org.semesteroppgave.models.utilities.inputhandler;

import javafx.scene.control.TableView;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.components.Component;
import org.semesteroppgave.models.exceptions.*;

/**
 * Input Validation Class for Component Object Creation / Editing
 */

public class InputValidation {

    public static String testValidVersion(String input) throws InvalidVersionException {

        String[] regex = {
                "^[A-ZÆØÅ]+([A-ZÆØÅa-zæøå0-9()[-],. ]){1,25}$",
                "^[0-9]+[[-]a-zæøå A-ZÆØÅ().,]{1,25}$"
        };

        for (String str : regex) {
            if (input.matches(str)) {
                return input;
            }
        }

        throw new InvalidVersionException("The version you entered is not valid." +
                "\nIt must be between 2 and 25 characters\nand start with a capital letter");
    }

    public static String testValidDescription(String input) throws InvalidDescriptionException{

        String regex = "[A-ZÆØÅ]+([A-ZÆØÅa-zæøå0-9()[-],. ]){4,100}$";

        if (input.matches(regex)) {
            return input;
        }

        throw new InvalidDescriptionException("The description you entered is not valid.\n" +
                "It must be between 5 and 100 characters\nnd start with a capital letter");

    }

    public static String testValidComponent(String input) throws InvalidComponentException {
        if (!input.toLowerCase().equals("motor") && !input.toLowerCase().equals("felg")
                && !input.toLowerCase().equals("setetrekk") && !input.toLowerCase().equals("dekk")
                && !input.toLowerCase().equals("spoiler") && !input.toLowerCase().equals("girboks")
                && !input.toLowerCase().equals("batteri") && !input.toLowerCase().equals("tank")) {
            throw new InvalidComponentException("It is not possible to create a: " + input);
        }
        return input;
    }

    public static double testValidPrice(double input) throws InvalidPriceException {
        String price = String.valueOf(input);
        String regex = "^[0-9]+(.|,)?[0-9]{1,3}$";
        if (price.matches(regex)){
            return input;
        }
        throw new InvalidPriceException("The price cannot be negative or have more than 3 decimal places");
    }

    public static void testComponentCount(TableView<Component> tableViewComponents, String input) throws InvalidDeleteException{
        int counter = 0;
        for (Component component : ApplicationData.getInstance().getRegisterComponent().getComponentList()) {
            if (component.getComponent().equals(tableViewComponents.getSelectionModel().getSelectedItem().getComponent())) {
                counter++;
            }
        }
        //If this occurs, there is only one such component left and an exception is thrown
        if (counter == 1) {
            tableViewComponents.refresh();
            throw new InvalidDeleteException("Can not " + input + " this component.\n" +
                    "There must be at least one of each component for the user to\nbe able to create a product");
        }
    }
}
