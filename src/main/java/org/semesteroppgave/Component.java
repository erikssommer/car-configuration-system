package org.semesteroppgave;

public abstract class Component {
    private String component;
    private String version;
    private double price;

    public Component(String component, String version, double price) {
        this.component = component;
        this.version = version;
        this.price = price;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getVersion() {
        return version;
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

}
