package org.semesteroppgave.models.utilities.helpers;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import org.semesteroppgave.models.filehandlers.FileHandler;

public class OpenWithThread extends Task<Void> {
    private final ProgressBar progressBar;

    public OpenWithThread(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    protected Void call() {
        try {
            Thread.sleep(1000);
            progressBar.setProgress(0.25);
            Thread.sleep(1000);
            progressBar.setProgress(0.50);
            Thread.sleep(1000);
            progressBar.setProgress(0.75);
            Thread.sleep(1000);
            progressBar.setProgress(1);
        } catch (InterruptedException e) {
            //Gjør ikke noe her
        }
        FileHandler.openFileJobjThread();
        return null;
    }
}
