package org.semesteroppgave.models.data.carcomponents;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FuelContainer extends Component {

    private transient String [] model;
    private transient String component;

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
        this.component = "Tank";
        this.model = new String[]{"Diesel", "Hybrid"};

        setVersion(version);
        setPrice(price);
        setDescription(description);
    }

    @Override
    public String toString(){
        return super.getVersion();
    }
}
