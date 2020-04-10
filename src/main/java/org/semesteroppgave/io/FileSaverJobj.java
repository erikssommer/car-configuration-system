package org.semesteroppgave.io;

import org.semesteroppgave.Context;
import org.semesteroppgave.RegisterComponent;
import org.semesteroppgave.carcomponents.Component;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSaverJobj implements FileSaver{

    @Override
    public void save(RegisterComponent registerComponent, Path filePath) throws IOException {
        try (OutputStream os = Files.newOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(os))
        {
            //out.writeObject(registerComponent);
            for (Component component : registerComponent.getComponentsList()){
                out.writeObject(component);
            }
            os.close();
            out.close();
        }
    }
}