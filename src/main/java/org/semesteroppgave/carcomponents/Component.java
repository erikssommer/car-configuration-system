package org.semesteroppgave.carcomponents;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public abstract class Component {

    private String version;
    private double price;
    private boolean added = false;

    public Component(String version, double price) {
        this.version = version;
        this.price = price;
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

    public boolean getAdded(){
        return added;
    }

    public void setAdded(boolean added){
        this.added = added;
    }
}
