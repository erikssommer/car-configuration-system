package org.semesteroppgave.models.utilities.threadhelper;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import org.semesteroppgave.models.filehandlers.FileHandler;

/**
 * Class that handles files in its own thread
 * Extends Task
 * Returns void as the file manager places the items directly into lists and is not returned
 */

public class OpenWithThread extends Task<Void> {

    private final ProgressBar progressBar;
    private final String filepath;

    public OpenWithThread(ProgressBar progressBar, String filepath) {
        this.progressBar = progressBar;
        this.filepath = filepath;
    }

    @Override
    protected Void call() {
        try {
            progressBar.setProgress(0.00);
            Thread.sleep(1000);
            progressBar.setProgress(0.25);
            Thread.sleep(1000);
            progressBar.setProgress(0.50);
            Thread.sleep(1000);
            progressBar.setProgress(0.75);
            Thread.sleep(1000);
            progressBar.setProgress(1);
        } catch (InterruptedException e) {
            //Don't do anything here
        }

        FileHandler.openFileJobjThread(filepath);

        return null;
    }
}
