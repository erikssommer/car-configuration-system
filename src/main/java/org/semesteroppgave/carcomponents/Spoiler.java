package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class Spoiler extends Component {

    private String [] model;
    private String component;

    public Spoiler(String version, double price, String... model) {
        super(version, price);
        this.component = "Spoiler";
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

    @Override
    public String toString(){
        return super.getVersion();
    }
}
