package org.semesteroppgave.models.data.carcomponents;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FuelContainer extends Component {

    private transient String component;
    private transient String [] model;

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

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        this.component = "Tank";
        this.model = new String[]{"Diesel", "Hybrid"};
    }
}
