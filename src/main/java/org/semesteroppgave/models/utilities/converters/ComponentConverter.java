package org.semesteroppgave.models.utilities.converters;

import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.exceptions.InvalidComponentException;

/**
 * Class that converts a Component object to a new subclass of Componnet
 * Determined by the name of the component edited in tableview
 */

public class ComponentConverter {

    public static Component convert(Component component) {
        switch (component.getComponent().toLowerCase()) {
            case "motor":
                return new Motor(component.getVersion(), component.getPrice(), component.getDescription());
            case "rim":
                return new Rim(component.getVersion(), component.getPrice(), component.getDescription());
            case "seat cover":
                return new SeatCover(component.getVersion(), component.getPrice(), component.getDescription());
            case "spoiler":
                return new Spoiler(component.getVersion(), component.getPrice(), component.getDescription());
            case "tires":
                return new Tires(component.getVersion(), component.getPrice(), component.getDescription());
            case "battery":
                return new Battery(component.getVersion(), component.getPrice(), component.getDescription());
            case "fuel container":
                return new FuelContainer(component.getVersion(), component.getPrice(), component.getDescription());
            case "gearbox":
                return new Gearbox(component.getVersion(), component.getPrice(), component.getDescription());

            default:
                throw new InvalidComponentException("Could not find component");
        }
    }
}
