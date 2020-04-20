package org.semesteroppgave.models.filehandlers.fileSave;

import org.semesteroppgave.Context;
import org.semesteroppgave.models.data.carcomponents.Component;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileSaverJobj implements FileSaver {

    @Override
    public void save(Path filePath) throws IOException {
        try (OutputStream os = Files.newOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(os))
        {
            out.writeObject(Context.getInstance().getRegisterComponent());

        }
    }
}
