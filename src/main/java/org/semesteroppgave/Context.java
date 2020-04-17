package org.semesteroppgave;

import org.semesteroppgave.models.data.RegisterComponent;
import org.semesteroppgave.models.data.RegisterProduct;

//Globale objekt som både bruker og admin må ha tilgang til

public class Context {

    private final static Context context = new Context();

    private Context(){}

    public static Context getInstance(){
        return context;
    }

    private RegisterComponent registerComponent = new RegisterComponent();
    private RegisterProduct registerProduct = new RegisterProduct();

    public RegisterComponent getRegisterComponent() {
        return this.registerComponent;
    }

    public RegisterProduct getRegisterProduct(){
        return this.registerProduct;
    }

    //Her kan vi fortsette å legge til globale objekter
}
