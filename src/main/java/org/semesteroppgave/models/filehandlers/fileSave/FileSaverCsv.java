package org.semesteroppgave.models.filehandlers.fileSave;

import org.semesteroppgave.ApplicationData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;

public class FileSaverCsv implements FileSaver {

    @Override
    public void save(Path filePath) {

        try (PrintWriter writer = new PrintWriter(new File(filePath.toUri()))) {
            StringBuilder sb = new StringBuilder();
            writeHeader(sb);
            sb.append("\n");
            sb.append(ApplicationData.getInstance().getRegisterProduct().toString());

            writer.write(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeHeader(StringBuilder sb) {
        String[] title = new String[]{"Model", "Model price", "Motor", "Price", "Description", "Rim", "Price", "Description",
                "Seat cover", "Price", "Description", "Spoiler", "Price", "Description", "Tires", "Price", "Description",
                "Battery", "Price", "Description", "Fuel container", "Price", "Description", "Gearbox", "Price", "Description",
                "Customization", "Price", "Customization", "Price", "Customization", "Price", "Customization", "Price"};

        for (String addTitle : title) {
            sb.append(addTitle);
            sb.append(";");
        }
        sb.append("Total price");
    }
}
