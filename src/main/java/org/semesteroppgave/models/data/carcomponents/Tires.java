package org.semesteroppgave.models.data.carcomponents;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Tires extends Component {

    private transient String component;
    private transient String [] model;

    public Tires(String version, double price, String description) {
        super(version, price, description);
        this.component = "Dekk";
        this.model = new String[]{"universial"};
    }

    public String [] getModel() {
        return model;
    }

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

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        this.component = "Dekk";
        this.model = new String[]{"universial"};
    }
}
