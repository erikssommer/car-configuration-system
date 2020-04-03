package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class Rim extends Component {

    private String [] model;
    private String component;

    public Rim(String version, double price, String description) {
        super(version, price, description);
        this.component = "Felg";
        this.model = new String[]{"universial"};
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