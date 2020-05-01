package org.semesteroppgave.models.utilities.converters;

import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.exceptions.InvalidComponentException;

/**
 * Klasse som konverterer et Component objekt til en ny underklasse av Componnet
 * Bestemmes av navnet på komponenten som er redigert i tableview
 */

public class ComponentConverter {

    public static Component convert(Component component){
        switch (component.getComponent().toLowerCase()) {
            case "motor":
                 return new Motor(component.getVersion(), component.getPrice(), component.getDescription());
            case "felg":
                return new Rim(component.getVersion(), component.getPrice(), component.getDescription());
            case "setetrekk":
                return new SeatCover(component.getVersion(), component.getPrice(), component.getDescription());
            case "spoiler":
                return new Spoiler(component.getVersion(), component.getPrice(), component.getDescription());
            case "dekk":
                return new Tires(component.getVersion(), component.getPrice(), component.getDescription());
            case "batteri":
                return new Battery(component.getVersion(), component.getPrice(), component.getDescription());
            case "tank":
                return new FuelContainer(component.getVersion(), component.getPrice(), component.getDescription());
            case "girboks":
                return new Gearbox(component.getVersion(), component.getPrice(), component.getDescription());

            default: throw new InvalidComponentException("Fant ikke komponenten");
        }
    }
}
