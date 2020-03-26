package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class Gearbox extends Component {

    private String component;

    public Gearbox(String version, double price) {
        super(version, price);
        this.component = "Girboks";
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
