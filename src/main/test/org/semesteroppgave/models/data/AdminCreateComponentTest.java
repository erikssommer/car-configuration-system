package org.semesteroppgave.models.data;

import org.junit.jupiter.api.Test;
import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.exceptions.InvalidDescriptionException;
import org.semesteroppgave.models.exceptions.InvalidVersionException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester for opprettelse av komponent
 */

class AdminCreateComponentTest {

    @Test
    void testValidAddComponent() {
        new Motor("700hk", 25000, "Motor for best ytelse - både i akselerasjon og toppfart");
        new Rim("16-tommer", 4000, "Felg som er standard. God komfort, ok utseende");
        new SeatCover("Komfort skinn", 10000, "Setetrekk i skinn som er komfortabelt for lange kjøreturer");
        new Spoiler("Høy og bred", 4000, "Spoiler for stor sportsutseende og maks veigrep i høy fart");
        new Tires("Pirelli Sommer", 5600, "Sommerdekk som er gode på alle typer sommerføre");
        new Battery("80 kWh", 15000, "EL-bilbatteri med rekkevidde på 500 km");
        new FuelContainer("40-liter", 5000, "Drivstofftank som er stor nok for de aller fleste");
        new Gearbox("Sporsautomat", 25000, "Automatgir som gir raske girskift og høy ytelse");

    }

    @Test
    void testInvalidAddComponent() {
        assertThrows(InvalidVersionException.class, () ->
                new Motor("wioef", 25000, "Motor for best ytelse - både i akselerasjon og toppfart"));

        assertThrows(InvalidVersionException.class, () ->
                new Rim("8347", 4000, "Felg som er standard. God komfort, ok utseende"));

        assertThrows(InvalidVersionException.class, () ->
                new SeatCover("K", 500, "Setetrekk i skinn som er komfortabelt for lange kjøreturer"));

        assertThrows(InvalidDescriptionException.class, () ->
                new Spoiler("Høy og bred", 4000, "hei"));

        assertThrows(InvalidDescriptionException.class, () ->
                new Tires("Pirelli Sommer", 5600, "lnpirfnm"));

        assertThrows(InvalidDescriptionException.class, () ->
                new Battery("80 kWh", 15000, "46578"));

        assertThrows(InvalidDescriptionException.class, () ->
                new FuelContainer("40-liter", 5000, "Beskrivelse på over 100 tegn er en del å skrive, men dette skal begyne å nærme seg noe, bare litt til og litt til"));

        assertThrows(InvalidDescriptionException.class, () ->
                new Gearbox("Sporsautomat", 25000, "sporsautomat"));

    }
}