module org.semesteroppgave {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.semesteroppgave to javafx.fxml;
    opens org.semesteroppgave.gui to javafx.fxml;
    opens org.semesteroppgave.carcomponents to javafx.base;

    exports org.semesteroppgave.carcomponents;
    exports org.semesteroppgave.carmodel;
    exports org.semesteroppgave;
}