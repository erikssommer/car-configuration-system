package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class Battery extends Component {

    private String component;

    public Battery(String version, double price) {
        super(version, price);
        this.component = "Batteri";
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
