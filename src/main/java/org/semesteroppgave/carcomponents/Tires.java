package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class Tires extends Component {

    private String component;

    public Tires(String version, double price) {
        super(version, price);
        this.component = "Dekk";
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
