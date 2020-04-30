package org.semesteroppgave.models.utilities.inputhandler;

import org.semesteroppgave.models.utilities.alerts.Dialogs;

/**
 * Klasse som viser en error melding ved galt input ved redigering i tableview
 * Avvik blir ikke kastet her fordi de kan ikke bli behandlet utenfor klassen.
 * Ved feil returnerer klassen 0.0.
 * "conversionSuccessful" er true hvis konverteringen er vellykket
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
                Dialogs.showErrorDialog("Feil", "Feil i pris", "Du må skrive inn et gyldig tall");
                conversionSuccessful = false;
                return 0.0;
            }
        }

        public boolean wasSuccessful() {
            return conversionSuccessful;
        }
    }
}
