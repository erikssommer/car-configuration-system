package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class Motor extends Component {

    private String model;
    private String component;

    public Motor(String version, double price, String model) {
        super(version, price);
        this.component = "Motor";
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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
}
