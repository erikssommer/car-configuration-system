package org.semesteroppgave;

public class Context {
    private final static Context context = new Context();

    public static Context getInstance(){
        return context;
    }

    private RegisterComponent registerComponent = new RegisterComponent();

    public RegisterComponent getRegisterComponent() {
        return registerComponent;
    }

    //Her kan vi fortsette å legge til globale objekter
}
