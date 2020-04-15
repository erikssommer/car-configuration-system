package org.semesteroppgave;

public class Context {

    private final static Context context = new Context();

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
