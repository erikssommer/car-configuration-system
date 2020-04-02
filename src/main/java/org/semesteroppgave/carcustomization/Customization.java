package org.semesteroppgave.carcustomization;

public abstract class Customization {
    private String customProperty;
    private double price;

    public Customization(String customProperty, double price){
        this.customProperty = customProperty;
        this.price = price;
    }

    public String getCustomProperty() {
        return customProperty;
    }

    public void setCustomProperty(String customProperty) {
        this.customProperty = customProperty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
