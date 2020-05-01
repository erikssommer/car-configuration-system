package org.semesteroppgave.models.utilities.converters;

import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.exceptions.InvalidComponentException;

/**
 * Klasse som konverterer et Component objekt til en ny underklasse av Componnet
 * Bestemmes av navnet på komponenten som er redigert i tableview
 */

public class ComponentConverter {

    public static Component convert(Component component){
        switch (component.getComponent()) {
            case "Motor":
                 return new Motor(component.getVersion(), component.getPrice(), component.getDescription());
            case "Felg":
                return new Rim(component.getVersion(), component.getPrice(), component.getDescription());
            case "Setetrekk":
                return new SeatCover(component.getVersion(), component.getPrice(), component.getDescription());
            case "Spoiler":
                return new Spoiler(component.getVersion(), component.getPrice(), component.getDescription());
            case "Dekk":
                return new Tires(component.getVersion(), component.getPrice(), component.getDescription());
            case "Batteri":
                return new Battery(component.getVersion(), component.getPrice(), component.getDescription());
            case "Tank":
                return new FuelContainer(component.getVersion(), component.getPrice(), component.getDescription());
            case "Girboks":
                return new Gearbox(component.getVersion(), component.getPrice(), component.getDescription());

            default: throw new InvalidComponentException("Fant ikke komponenten");
        }
    }
}
