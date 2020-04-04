package org.semesteroppgave.carcomponents;

public class SteeringWheel extends Component {

    private String [] model;
    private String component;

    public SteeringWheel(String version, double price, String description) {
        super(version, price, description);
        this.component = "Ratt";
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
