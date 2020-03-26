package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class Motor extends Component {

    private String component;

    public Motor(String version, double price) {
        super(version, price);
        this.component = "Motor";
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
}
