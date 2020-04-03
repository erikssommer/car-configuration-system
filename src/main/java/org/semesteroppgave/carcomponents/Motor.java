package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class Motor extends Component {

    private String [] model;
    private String component;

    public Motor(String version, double price, String description) {
        super(version, price, description);
        this.component = "Motor";
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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Override
    public String toString(){
        return super.getVersion();
    }
}
