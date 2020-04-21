package org.semesteroppgave.models.filehandlers.fileOpen;

import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.RegisterComponent;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileOpenerJobj implements FileOpener {

    @Override
    public void open(Path filePath) throws IOException {
        try (InputStream fin = Files.newInputStream(filePath);
             ObjectInputStream oin = new ObjectInputStream(fin)) {

            RegisterComponent register = (RegisterComponent) oin.readObject();
            ApplicationData.getInstance().getRegisterComponent().getComponentsList().clear();
            register.getComponentsList().forEach(ApplicationData.getInstance().getRegisterComponent()::setComponentsList);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
