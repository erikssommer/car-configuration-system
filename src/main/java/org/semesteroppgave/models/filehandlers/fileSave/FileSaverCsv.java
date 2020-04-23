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
        String[] title = new String[]{"Model", "Modelpris", "Motor", "Pris", "Beskrivelse", "Felg", "Pris", "Beskrivelse",
                "Setetrekk", "Pris", "Beskrivelse", "Spoiler", "Pris", "Beskrivelse", "Dekk", "Pris", "Beskrivelse",
                "Batteri", "Pris", "Beskrivelse", "Tank", "Pris", "Beskrivelse", "Girboks", "Pris", "Beskrivelse",
                "Tilpasning", "Pris", "Tilpasning", "Pris", "Tilpasning", "Pris", "Tilpasning", "Pris"};

        for (String addTitle : title) {
            sb.append(addTitle);
            sb.append(";");
        }
        sb.append("Totalpris");
    }
}
