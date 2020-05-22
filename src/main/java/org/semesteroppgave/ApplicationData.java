package org.semesteroppgave;

import org.semesteroppgave.models.data.registrationdata.RegisterComponent;
import org.semesteroppgave.models.data.registrationdata.RegisterProduct;

/**
 * Global object that both user and admin must have access to
 * Singleton design pattern to guarantee that only one object of class is created
 * Provides global access
 */

public class ApplicationData {

    private static final ApplicationData APPLICATION_DATA = new ApplicationData();

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

}
