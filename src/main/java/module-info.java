module org.semesteroppgave {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.semesteroppgave.controller to javafx.fxml;

    exports org.semesteroppgave.models.data.customizations;
    exports org.semesteroppgave.models.data.components;
    exports org.semesteroppgave.models.data.productmodels;
    exports org.semesteroppgave.models.data.registrationdata;
    exports org.semesteroppgave.models.utilities.converters;
    exports org.semesteroppgave.models.exceptions;
    exports org.semesteroppgave.models.utilities.search;
    exports org.semesteroppgave;
}