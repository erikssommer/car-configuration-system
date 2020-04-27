module org.semesteroppgave {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.semesteroppgave.controller to javafx.fxml;
    opens org.semesteroppgave.models.data to javafx.base;

    exports org.semesteroppgave.models.data.customizations;
    exports org.semesteroppgave.models.data.components;
    exports org.semesteroppgave.models.data.productmodels;
    exports org.semesteroppgave.models.data;
    exports org.semesteroppgave;
}