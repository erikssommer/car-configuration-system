package org.semesteroppgave.carcomponents;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Battery extends Component {

    private transient String [] model;
    private transient String component;

    public Battery(String version, double price, String description) {
        super(version, price, description);
        this.component = "Batteri";
        this.model = new String[]{"Elektrisk", "Hybrid"};
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(getVersion());
        s.writeDouble(getPrice());
        s.writeUTF(getDescription());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String version = s.readUTF();
        double price = s.readDouble();
        String description = s.readUTF();

        this.setVersion();
        this.setPrice();
        this.setDescription();
        this.component = "Batteri";
        this.model = new String[]{"Elektrisk", "Hybrid"};

        setVersion(version);
        setPrice(price);
        setDescription(description);
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
