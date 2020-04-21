package org.semesteroppgave.models.data.carcomponents;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Gearbox extends Component {

    private transient String component;
    private transient String [] model;

    public Gearbox(String version, double price, String description) {
        super(version, price, description);
        this.component = "Girboks";
        this.model = new String[]{"Diesel"};
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
        this.component = "Girboks";
        this.model = new String[]{"Diesel"};
    }
}
