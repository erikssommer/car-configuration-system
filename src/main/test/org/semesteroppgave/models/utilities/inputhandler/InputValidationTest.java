package org.semesteroppgave.models.utilities.inputhandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.semesteroppgave.models.exceptions.InvalidComponentException;
import org.semesteroppgave.models.exceptions.InvalidDeleteException;
import org.semesteroppgave.models.exceptions.InvalidDescriptionException;
import org.semesteroppgave.models.exceptions.InvalidVersionException;

class InputValidationTest {

    @Test
    void testValidVersion() {
        InputValidation.testValidVersion("100hk");
        InputValidation.testValidVersion("16-tommer");
        InputValidation.testValidVersion("Komfort stoff");
        InputValidation.testValidVersion("Høy og bred");
        InputValidation.testValidVersion("Sportsautomat");
        InputValidation.testValidVersion("40-liter");
        InputValidation.testValidVersion("20 kWh");
        InputValidation.testValidVersion("Goodyear Sommer Sport");
        InputValidation.testValidVersion("20-tommer (aluminium)");
        InputValidation.testValidVersion("Goodyear Vinter Piggfri");
        InputValidation.testValidVersion("Tank (10L),- med kork.");
    }

    @Test
    void testInvalidVersion() {
        Assertions.assertThrows(InvalidVersionException.class, () -> InputValidation.testValidVersion(""));
        Assertions.assertThrows(InvalidVersionException.class, () -> InputValidation.testValidVersion("000"));
        Assertions.assertThrows(InvalidVersionException.class, () -> InputValidation.testValidVersion("Dette er ca to tegn for mye"));
        Assertions.assertThrows(InvalidVersionException.class, () -> InputValidation.testValidVersion("%/?"));
        Assertions.assertThrows(InvalidVersionException.class, () -> InputValidation.testValidVersion(" "));
        Assertions.assertThrows(InvalidVersionException.class, () -> InputValidation.testValidVersion("  "));
    }

    @Test
    void testValidDescription() {
        InputValidation.testValidDescription("Spoiler for medium sportsutseende og medium veigrep i høy fart");
        InputValidation.testValidDescription("EL-bilbatteri med rekkevidde på 150 km");
        InputValidation.testValidDescription("Motor for ok ytelse og ok fart");
        InputValidation.testValidDescription("Felg som er standard. God komfort, ok utseende");
        InputValidation.testValidDescription("Setetrekk i stoff som gir god støtte for mer sportslig kjøring");
        InputValidation.testValidDescription("Spoiler for medium sportsutseende og maks veigrep i høy fart");
        InputValidation.testValidDescription("Enkelt manuelt gir. Komfortabelt, men innbyr også til sportslighet");
        InputValidation.testValidDescription("Drivstofftank som er stor nok for de aller fleste");
        InputValidation.testValidDescription("Vinterdekk som er gode på alle typer vinterføre, men spesielt bra på is");
    }

    @Test
    void testInvalidDescription() {
        Assertions.assertThrows(InvalidDescriptionException.class, () -> InputValidation.testValidDescription(" "));
        Assertions.assertThrows(InvalidDescriptionException.class, () -> InputValidation.testValidDescription("B"));
        Assertions.assertThrows(InvalidDescriptionException.class, () -> InputValidation.testValidDescription("b"));
        Assertions.assertThrows(InvalidDescriptionException.class, () -> InputValidation.testValidDescription("beskrivelse"));
        Assertions.assertThrows(InvalidDescriptionException.class, () -> InputValidation.testValidDescription("00000"));
        Assertions.assertThrows(InvalidDescriptionException.class, () -> InputValidation.testValidDescription("Beskrivelse på over 100 tegn er en del å skrive, men dette skal begyne å nærme seg noe, bare litt til og litt til"));
        Assertions.assertThrows(InvalidDescriptionException.class, () -> InputValidation.testValidDescription("%/?+&#"));
    }

    @Test
    void testValidComponent() {
        InputValidation.testValidComponent("Batteri");
        InputValidation.testValidComponent("Motor");
        InputValidation.testValidComponent("Felg");
        InputValidation.testValidComponent("Setetrekk");
        InputValidation.testValidComponent("Dekk");
        InputValidation.testValidComponent("Girboks");
        InputValidation.testValidComponent("Tank");
    }

    @Test
    void testInvalidComponent() {
        Assertions.assertThrows(InvalidComponentException.class, () -> InputValidation.testValidComponent(""));
        Assertions.assertThrows(InvalidComponentException.class, () -> InputValidation.testValidComponent(" "));
        Assertions.assertThrows(InvalidComponentException.class, () -> InputValidation.testValidComponent("1"));
        Assertions.assertThrows(InvalidComponentException.class, () -> InputValidation.testValidComponent("Moto"));
        Assertions.assertThrows(InvalidComponentException.class, () -> InputValidation.testValidComponent("ank"));
        Assertions.assertThrows(InvalidComponentException.class, () -> InputValidation.testValidComponent("Understell"));
    }
}