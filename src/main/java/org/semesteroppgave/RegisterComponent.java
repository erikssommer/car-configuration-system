package org.semesteroppgave;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.semesteroppgave.carcomponents.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterComponent implements Serializable {

    private static final long serialVersionUID = 1;

    //Dette er hovedlisten
    private transient ObservableList<Component> componentsList = FXCollections.observableArrayList();
    private transient ObservableList<Component> searchResult = FXCollections.observableArrayList();
    private transient ObservableList<Component> modelComponentsList = FXCollections.observableArrayList();
    private transient ObservableList<Component> chooseComponentList = FXCollections.observableArrayList();
    private transient ObservableList<Component> createComponentList = FXCollections.observableArrayList();
    private transient String newComponent;

    public RegisterComponent(ObservableList<Component> componentsList){
        this.componentsList = componentsList;
    }

    public RegisterComponent(){}

    public void writeObject(ObjectOutputStream s) throws IOException, ClassNotFoundException{
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(componentsList));
    }

    @SuppressWarnings("unchecked")
    public void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
        List<Component> newComponentList = (List<Component>) s.readObject();
        componentsList = FXCollections.observableArrayList();
        componentsList.addAll(newComponentList);
    }

    //Brukes til å finne ut hvilken type komponent som skal opprettet av admin
    public void setNewComponent(String newComponent){
        this.newComponent = newComponent;
    }

    public String getNewComponent(){
        return this.newComponent;
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

    public void setModelComponentsList(Component component){
        modelComponentsList.add(component);
    }

    public ObservableList<Component> getModelComponentsList(){
        return this.modelComponentsList;
    }

    public void setChooseComponentList(Component component){
        chooseComponentList.add(component);
    }

    public ObservableList<Component> getChooseComponentList(){
        return this.chooseComponentList;
    }

    public void setCreateComponentList(Component component){
        createComponentList.add(component);
    }

    public ObservableList<Component> getCreateComponentList(){
        return this.createComponentList;
    }

    public void setSearchResult(Component component){
        searchResult.add(component);
    }

    public ObservableList<Component> getSearchResult(){
        return this.searchResult;
    }

    public void removeAll() {
        componentsList.clear();
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
