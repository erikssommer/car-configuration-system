package org.semesteroppgave.io;

import org.semesteroppgave.Context;
import org.semesteroppgave.RegisterComponent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSaverCsv implements FileSaver{

    @Override
    public void save(Path filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new File(filePath.toUri()))){
            StringBuilder sb = new StringBuilder();
            writeHeader(sb);
            sb.append("\n");
            sb.append(Context.getInstance().getRegisterProduct().toString());
            sb.append("\n");

            writer.write(sb.toString());
            System.out.println("Done");

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private void writeHeader(StringBuilder sb){
        String [] title = new String[]{"Model", "Modelpris", "Motor", "Pris", "Beskrivelse", "Felg", "Pris", "Beskrivelse",
        "Setetrekk", "Pris", "Beskrivelse", "Spoiler", "Pris", "Beskrivelse", "Dekk", "Pris", "Beskrivelse",
        "Batteri", "Pris", "Beskrivelse", "Tank", "Pris", "Beskrivelse", "Girboks", "Pris", "Beskrivelse",
        "Tilpasning", "Pris", "Beskrivelse", "Tilpasning", "Pris", "Beskrivelse", "Tilpasning", "Pris", "Beskrivelse",
        "Tilpasning", "Pris", "Beskrivelse", "Totalpris"};

        for (String addTitle : title){
            sb.append(addTitle);
            sb.append(";");
        }
    }
}
