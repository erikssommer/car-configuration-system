package org.semesteroppgave.models.data.registrationdata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.semesteroppgave.models.data.components.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global class (retrieved from ApplicationData) which holds on the component list
 * The class is serialized to enable the global object of the class found in ApplicationData
 * is written to serialized jobj file
 * Implementes writeObject and readObject methods, so Java uses them instead of standard methods
 * The class also contains search function with streams
 */

public class RegisterComponent implements Serializable {

    private static final long serialVersionUID = 1;

    private transient ObservableList<Component> componentList = FXCollections.observableArrayList();

    public void setComponentList(ObservableList<Component> componentList) {
        this.componentList = componentList;
    }

    public void setComponentList(Component component) {
        componentList.add(component);
    }

    public ObservableList<Component> getComponentList() {
        return this.componentList;
    }

    //Search feature with streams
    public ObservableList<Component> searchComponent(String value) {

        return componentList.stream().filter(component -> component.getComponent().toLowerCase()
                .contains(value.toLowerCase())).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Component> searchVersion(String value) {

        return componentList.stream().filter(component -> component.getVersion().toLowerCase()
                .contains(value.toLowerCase())).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    //Turn the price into a string to get a more user-friendly search
    public ObservableList<Component> searchPrice(String value) {

        return componentList.stream().filter(component -> String.valueOf(component.getPrice()).contains(value))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private void writeObject(ObjectOutputStream s) throws IOException, ClassNotFoundException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(componentList));
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        List<Component> newComponentList = (List<Component>) s.readObject();
        componentList = FXCollections.observableArrayList();
        componentList.addAll(newComponentList);
    }
}
