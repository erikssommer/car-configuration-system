package org.semesteroppgave.models.filehandlers.fileOpen;

import org.semesteroppgave.Context;
import org.semesteroppgave.models.data.RegisterComponent;
import org.semesteroppgave.models.data.carcomponents.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileOpenerJobj implements FileOpener {

    @Override
    public void open(Path filePath) throws IOException {
        try (InputStream fin = Files.newInputStream(filePath);
             ObjectInputStream oin = new ObjectInputStream(fin)) {

            RegisterComponent register = (RegisterComponent) oin.readObject();
            Context.getInstance().getRegisterComponent().getComponentsList().clear();
            register.getComponentsList().forEach(Context.getInstance().getRegisterComponent()::setComponentsList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
