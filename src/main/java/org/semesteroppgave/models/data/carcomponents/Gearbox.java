package org.semesteroppgave.models.data.carcomponents;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Gearbox extends Component {

    private transient String [] model;
    private transient String component;

    public Gearbox(String version, double price, String description) {
        super(version, price, description);
        this.component = "Girboks";
        this.model = new String[]{"Diesel"};
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

    public void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        super.writeObject(s);
    }

    public void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        super.readObject(s);
        this.component = "Girboks";
        this.model = new String[]{"Diesel"};
    }
}
