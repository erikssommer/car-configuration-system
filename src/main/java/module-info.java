module org.semesteroppgave {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.semesteroppgave to javafx.fxml;
    opens org.semesteroppgave.controller to javafx.fxml;
    opens org.semesteroppgave.models.data to javafx.base;

    exports org.semesteroppgave.models.data.carcustomization;
    exports org.semesteroppgave.models.data.carcomponents;
    exports org.semesteroppgave.models.data.carmodel;
    exports org.semesteroppgave.models.data;
    exports org.semesteroppgave;
}