package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class SteeringWheel extends Component {

    private String [] model;
    private String component;

    public SteeringWheel(String version, double price, String... model) {
        super(version, price);
        this.component = "Ratt";
        this.model = model;
    }

    @Override
    public String [] getModel() {
        return model;
    }

    @Override
    public void setModel(String [] model) {
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
