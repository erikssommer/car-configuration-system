package org.semesteroppgave;

public class FuelContainer extends Component{

    private String component;

    public FuelContainer(String version, double price) {
        super(version, price);
        this.component = "Tank";
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
