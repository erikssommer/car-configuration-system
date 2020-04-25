module org.semesteroppgave {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.semesteroppgave to javafx.fxml;
    opens org.semesteroppgave.controller to javafx.fxml;
    opens org.semesteroppgave.models.data to javafx.base;

    exports org.semesteroppgave.models.data.productcustomization;
    exports org.semesteroppgave.models.data.productcomponents;
    exports org.semesteroppgave.models.data.productmodel;
    exports org.semesteroppgave.models.data;
    exports org.semesteroppgave;
}