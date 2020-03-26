package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class Rim extends Component {

    private String component;

    public Rim(String version, double price) {
        super(version, price);
        this.component = "Felg";
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
