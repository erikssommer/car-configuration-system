package org.semesteroppgave;

import org.semesteroppgave.models.data.RegisterComponent;
import org.semesteroppgave.models.data.RegisterProduct;

//Globale objekt som både bruker og admin må ha tilgang til

public class ApplicationData {

    private final static ApplicationData APPLICATION_DATA = new ApplicationData();

    private ApplicationData() {
    }

    public static ApplicationData getInstance() {
        return APPLICATION_DATA;
    }

    private final RegisterComponent registerComponent = new RegisterComponent();
    private final RegisterProduct registerProduct = new RegisterProduct();

    public RegisterComponent getRegisterComponent() {
        return this.registerComponent;
    }

    public RegisterProduct getRegisterProduct() {
        return this.registerProduct;
    }

    //Her kan vi fortsette å legge til globale objekter
}
