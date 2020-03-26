package org.semesteroppgave;

public class Spoiler extends Component{

    private String component;

    public Spoiler(String version, double price) {
        super(version, price);
        this.component = "Spioler";
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
