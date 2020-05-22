package org.semesteroppgave.models.utilities.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.function.Consumer;

/**
 * Dialogs that provide information to the user when using pop-up boxes
 */

public class Dialogs {

    public static void showErrorDialog(String errorTitle, String errorHeader, String errorContent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(errorTitle);
        alert.setHeaderText(errorHeader);
        alert.setContentText(errorContent);

        alert.showAndWait();
    }

    public static void showWarningDialog(String warningTitle, String warningHeader, String warningContent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(warningTitle);
        alert.setHeaderText(warningHeader);
        alert.setContentText(warningContent);

        alert.showAndWait();
    }

    public static void showSuccessDialog(String successTitle, String successHeader, String successContent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(successTitle);
        alert.setHeaderText(successHeader);
        alert.setContentText(successContent);

        alert.showAndWait();
    }

    public static void showConfirmationDialog(String header, String content, Consumer<? super ButtonType> action) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait().ifPresent(action);
    }
}
