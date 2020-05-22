package org.semesteroppgave.models.filehandlers.fileOpen;

import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.registrationdata.RegisterComponent;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class that reads from serialized job file and creates components
 * Adds the components to the component list
 */

public class FileOpenerJobj implements FileOpener {

    @Override
    public void open(Path filePath) throws IOException {
        try (InputStream fin = Files.newInputStream(filePath);
             ObjectInputStream oin = new ObjectInputStream(fin)) {

            RegisterComponent register = (RegisterComponent) oin.readObject();
            ApplicationData.getInstance().getRegisterComponent().getComponentList().clear();
            register.getComponentList().forEach(ApplicationData.getInstance().getRegisterComponent()::setComponentList);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
