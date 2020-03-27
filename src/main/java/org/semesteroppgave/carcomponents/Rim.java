package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class Rim extends Component {

    private String model;
    private String component;

    public Rim(String version, double price, String model) {
        super(version, price);
        this.component = "Felg";
        this.model = model;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String getComponent() {
        return component;
    }

    @Override
    public void setComponent(String component) {
        this.component = component;
    }
}
