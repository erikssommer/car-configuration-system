module org.semesteroppgave {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.semesteroppgave to javafx.fxml;
    opens org.semesteroppgave.gui to javafx.fxml;
    exports org.semesteroppgave;
}