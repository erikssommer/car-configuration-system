package org.semesteroppgave.models.utilities.threadhelper;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import org.semesteroppgave.models.filehandlers.FileHandler;

/**
 * Klasse som behandler filer i egen tråd
 * Utvides av Task
 * Returnerer void ettersom filbehandlingen legger objektene rett inn i lister og ikke blir returnert
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
            //Gjør ikke noe her
        }

        FileHandler.openFileJobjThread(filepath);

        return null;
    }
}
