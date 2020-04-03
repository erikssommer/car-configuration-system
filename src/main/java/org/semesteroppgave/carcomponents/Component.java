package org.semesteroppgave.carcomponents;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public abstract class Component {

    private String version;
    private double price;
    private String description;

    public Component(String version, double price, String desciption) {
        if (version.isEmpty() || desciption.isEmpty()) throw new NullPointerException("Du har glemt å fylle inn versjon eller beskrivelse");
        this.version = version;
        this.price = price;
        this.description = desciption;
    }

    public abstract String [] getModel();
    public abstract void setModel(String [] model);
    public abstract String getComponent();
    public abstract void setComponent(String component);

    public String getVersion(){
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}
