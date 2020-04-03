package org.semesteroppgave.carcomponents;

import org.semesteroppgave.carcomponents.Component;

public class SeatCover extends Component {

    private String [] model;
    private String component;

    public SeatCover(String version, double price, String description) {
        super(version, price, description);
        this.component = "Setetrekk";
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