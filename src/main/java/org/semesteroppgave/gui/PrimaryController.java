package org.semesteroppgave.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.semesteroppgave.Context;
import org.semesteroppgave.Main;
import org.semesteroppgave.carcomponents.*;

public class PrimaryController implements Initializable {

    //Dette er bare for testing
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Motor motor = new Motor("V12",12300,"universial");
        Context.getInstance().getRegisterComponent().setComponentsList(motor);
        Motor motor2 = new Motor("V8",1230,"universial");
        Context.getInstance().getRegisterComponent().setComponentsList(motor2);
        Rim rim = new Rim("Feit",3900,"universial");
        Context.getInstance().getRegisterComponent().setComponentsList(rim);
        SeatCover seatCover = new SeatCover("Mykt",123,"universial");
        Context.getInstance().getRegisterComponent().setComponentsList(seatCover);
        SeatCover seatCover2 = new SeatCover("Sport",12300,"universial");
        Context.getInstance().getRegisterComponent().setComponentsList(seatCover2);
        Spoiler spoiler = new Spoiler("Høy",1000,"universial");
        Context.getInstance().getRegisterComponent().setComponentsList(spoiler);
        Spoiler spoiler2 = new Spoiler("Lav",500,"universial");
        Context.getInstance().getRegisterComponent().setComponentsList(spoiler2);
        Tires tires = new Tires("Vinter",1000,"universial");
        Context.getInstance().getRegisterComponent().setComponentsList(tires);
        Tires tires2 = new Tires("Sommer",1500,"universial");
        Context.getInstance().getRegisterComponent().setComponentsList(tires2);
        Tires tires3 = new Tires("Alround",2000,"universial");
        Context.getInstance().getRegisterComponent().setComponentsList(tires3);
    }

    @FXML
    void btnAdmin(ActionEvent event) throws IOException {
        Main.setRoot("adminsignin");
    }

    @FXML
    void btnUser(ActionEvent event) throws IOException {
        Main.setRoot("usersignin");
    }
}
