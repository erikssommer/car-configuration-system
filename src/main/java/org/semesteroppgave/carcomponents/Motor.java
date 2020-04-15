package org.semesteroppgave.carcomponents;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Motor extends Component {

    private transient String [] model;
    private transient String component;

    public Motor(String version, double price, String description) {
        super(version, price, description);
        this.component = "Motor";
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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
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
        this.component = "Motor";
        this.model = new String[]{"universial"};

        setVersion(version);
        setPrice(price);
        setDescription(description);
    }

    @Override
    public String toString(){
        return super.getVersion();
    }
}
