package org.semesteroppgave.io;

import org.semesteroppgave.Context;
import org.semesteroppgave.RegisterComponent;
import org.semesteroppgave.carcomponents.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileOpenerJobj implements FileOpener{

    @Override
    public void open(Path filePath) throws IOException {
        try (InputStream fin = Files.newInputStream(filePath);
             ObjectInputStream oin = new ObjectInputStream(fin)) {

            ArrayList<Component> list = (ArrayList<Component>) oin.readObject();
            Context.getInstance().getRegisterComponent().getComponentsList().addAll(list);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
