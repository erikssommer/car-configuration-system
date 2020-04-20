package org.semesteroppgave.models.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.semesteroppgave.models.data.carcomponents.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterComponent implements Serializable{

    private static final long serialVersionUID = 1;

    //Dette er hovedlisten
    private transient ObservableList<Component> componentsList = FXCollections.observableArrayList();

    public RegisterComponent(ObservableList<Component> componentsList){
        this.componentsList = componentsList;
    }

    public RegisterComponent(){}

    private void writeObject(ObjectOutputStream s) throws IOException, ClassNotFoundException{
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(componentsList));
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
        List<Component> newComponentList = (List<Component>) s.readObject();
        componentsList = FXCollections.observableArrayList();
        componentsList.addAll(newComponentList);
    }

    public void setComponentsList(ObservableList<Component> componentsList){
        this.componentsList = componentsList;
    }

    public void setComponentsList(Component component){
        componentsList.add(component);
    }

    public ObservableList<Component> getComponentsList(){
        return this.componentsList;
    }

    //Søkefunksjon med streams
    public ObservableList<Component> searchComponent(String value){

        return componentsList.stream().filter(component -> component.getComponent().toLowerCase().contains(value.toLowerCase())).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Component> searchVersion(String value){

        return componentsList.stream().filter(component -> component.getVersion().toLowerCase().contains(value.toLowerCase())).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Component> searchPrice(double value){

        return componentsList.stream().filter(component -> component.getPrice()==(value)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
}
