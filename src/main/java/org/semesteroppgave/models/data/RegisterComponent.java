package org.semesteroppgave.models.data;

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
 * Global klasse (hentes fra ApplicationData) som holder på componentlisten
 * Klassen er serialisert slik at det globale objektet av klassen, funnet i ApplicationData, skal kunne
 * skrives til serialisert jobj-fil
 * Implementerer writeObject og readObject metodene, slik at Java bruker disse istedenfor standard-metodene
 * Klassen inneholder også søkefunksjon med streams
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

    //Søkefunksjon med streams
    public ObservableList<Component> searchComponent(String value) {

        return componentList.stream().filter(component -> component.getComponent().toLowerCase()
                .contains(value.toLowerCase())).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Component> searchVersion(String value) {

        return componentList.stream().filter(component -> component.getVersion().toLowerCase()
                .contains(value.toLowerCase())).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Component> searchPrice(double value) {

        return componentList.stream().filter(component -> component.getPrice() == (value))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private void writeObject(ObjectOutputStream s) throws IOException, ClassNotFoundException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(componentList));
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        List<Component> newComponentList = (List<Component>) s.readObject();
        componentList = FXCollections.observableArrayList();
        componentList.addAll(newComponentList);
    }
}
