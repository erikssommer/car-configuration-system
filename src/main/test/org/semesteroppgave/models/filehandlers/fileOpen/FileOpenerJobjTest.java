package org.semesteroppgave.models.filehandlers.fileOpen;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

class FileOpenerJobjTest {

    @Test
    void open() {
        FileOpener fileOpener = new FileOpenerJobj();
        try {
            fileOpener.open(Paths.get("files/testFiles/testOpenJobj.jobj"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}