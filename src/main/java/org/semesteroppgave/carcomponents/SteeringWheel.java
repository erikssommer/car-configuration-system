package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class SteeringWheel extends Component {

    private String component;

    public SteeringWheel(String version, double price) {
        super(version, price);
        this.component = "Ratt";
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
