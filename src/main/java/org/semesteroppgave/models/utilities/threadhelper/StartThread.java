package org.semesteroppgave.models.utilities.threadhelper;

import javafx.concurrent.WorkerStateEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.semesteroppgave.controller.ApplicationThread;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

/**
 * Model for the controllers that process threads
 * Here, the ApplicationThread interface is used to enable gui after thread processing
 * The class calls on OpenWithThread as a thread
 */

public class StartThread {

    private final ApplicationThread applicationThread;
    private final Label lblThreadMessage;
    private final ProgressBar progressBar;

    public StartThread(ApplicationThread applicationThread, Label lblThreadMessage, ProgressBar progressBar) {
        this.applicationThread = applicationThread;
        this.lblThreadMessage = lblThreadMessage;
        this.progressBar = progressBar;
    }

    public void start(String filePath) {
        lblThreadMessage.setText("Loading file...");
        OpenWithThread openWithThread = new OpenWithThread(progressBar, filePath);
        openWithThread.setOnSucceeded(this::fileOpened);
        openWithThread.setOnFailed(this::fileOpeningFailed);
        Thread thread = new Thread(openWithThread);
        thread.setDaemon(true);
        applicationThread.disableGUI();
        thread.start();
    }

    private void fileOpened(WorkerStateEvent e) {
        lblThreadMessage.setText("Done");
        applicationThread.enableGUI();
    }

    private void fileOpeningFailed(WorkerStateEvent e) {
        lblThreadMessage.setText("Error in thread");
        Dialogs.showErrorDialog("File", "Error opening file", e.getSource().getException().getMessage());
        applicationThread.enableGUI();
    }
}
