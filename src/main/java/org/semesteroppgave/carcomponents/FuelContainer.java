package org.semesteroppgave.carcomponents;

public class FuelContainer extends Component {

    private String [] model;
    private String component;

    public FuelContainer(String version, double price, String description) {
        super(version, price, description);
        this.component = "Tank";
        this.model = new String[]{"Diesel", "Hybrid"};
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
