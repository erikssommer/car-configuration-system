package org.semesteroppgave.models.data.carcomponents;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FuelContainer extends Component {

    private transient String component;
    private transient ArrayList<String> model;

    public FuelContainer(String version, double price, String description) {
        super(version, price, description);
        this.component = "Tank";
        this.model = new ArrayList<>();
        this.model.add("Diesel");
        this.model.add("Hybrid");
    }

    public ArrayList<String> getModel() {
        return model;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Override
    public String toString() {
        return super.getVersion();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        this.component = "Tank";
        this.model = new ArrayList<>();
        this.model.add("Diesel");
        this.model.add("Hybrid");
    }
}
