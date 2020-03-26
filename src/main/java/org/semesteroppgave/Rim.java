package org.semesteroppgave;

public class Rim extends Component{

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
