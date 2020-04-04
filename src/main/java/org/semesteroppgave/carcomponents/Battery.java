package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class Battery extends Component {

    private String [] model;
    private String component;

    public Battery(String version, double price, String description) {
        super(version, price, description);
        this.component = "Batteri";
        this.model = new String[]{"Elektrisk", "Hybrid"};
    }

    @Override
    public String [] getModel() {
        return this.model;
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
