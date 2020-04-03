package org.semesteroppgave;

import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.exceptions.InvalidComponentException;
import org.semesteroppgave.gui.Dialogs;

public class ComponentConverter {
    public static void convert(Component component){
        Component newComponent;
        switch (component.getComponent()){
            case "Motor": newComponent = new Motor(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Felg": newComponent = new Rim(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Setetrekk": newComponent = new SeatCover(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Spoiler": newComponent = new Spoiler(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Dekk": newComponent = new Tires(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Batteri": newComponent = new Battery(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Tank": newComponent = new FuelContainer(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Girboks": newComponent = new Gearbox(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            default: throw new InvalidComponentException("Fant ikke komponenten");
        }
        int index = Context.getInstance().getRegisterComponent().getComponentsList().indexOf(component);
        Context.getInstance().getRegisterComponent().getComponentsList().remove(component);
        //Her plasserer jeg det nye objektet på den tidligere plassen til det gamle objektet
        Context.getInstance().getRegisterComponent().getComponentsList().set(index, newComponent);
    }
}
