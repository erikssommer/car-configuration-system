package org.semesteroppgave.models.utilities.converters;

import org.semesteroppgave.models.utilities.alerts.Dialogs;

/**
 * Class showing an error message with wrong input when editing in tableview
 * Exceptions are not thrown here because they cannot be dealt with outside the class.
 * In case of failure, the class returns 0.0
 * "conversionSuccessful" is true if conversion is successful
 */

public class DoubleConverter {

    public static class DoubleStringConverter extends javafx.util.converter.DoubleStringConverter {
        private boolean conversionSuccessful;

        @Override
        public Double fromString(String s) {
            try {
                Double result = super.fromString(s);
                conversionSuccessful = true;
                return result;
            } catch (NumberFormatException e) {
                Dialogs.showErrorDialog("Error", "Price error", "You must enter a valid number");
                conversionSuccessful = false;
                return 0.0;
            }
        }

        public boolean wasSuccessful() {
            return conversionSuccessful;
        }
    }
}
