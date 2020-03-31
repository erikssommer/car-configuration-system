package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.semesteroppgave.Context;
import org.semesteroppgave.Main;
import org.semesteroppgave.carcomponents.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminCreateController implements Initializable {

    private Component newComponent = null;

    @FXML
    private Label lblComponent;

    @FXML
    private TextField txtVersion, txtPrice;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TableView<Component> tableViewAddedConfig;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblComponent.setText(Context.getInstance().getRegisterComponent().getNewComponent().toLowerCase());
    }

    @FXML
    void btnAdd(ActionEvent event) {
        switch (Context.getInstance().getRegisterComponent().getNewComponent()){
            case "Motor": newComponent = new Motor(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), "universial");
                break;
            case "Felg": newComponent = new Rim(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), "universial");
                break;
            case "Setetrekk": newComponent = new SeatCover(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), "universial");
                break;
            case "Ratt": newComponent = new SteeringWheel(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), "universial");
                break;
            case "Spoiler": newComponent = new Spoiler(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), "universial");
                break;
            case  "Dekk": newComponent = new Tires(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), "universial");
                break;
            case "Batteri": newComponent = new Battery(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), "Hybrid","Electric");
                break;
            case "Tank": newComponent = new FuelContainer(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), "Diesel", "Hybrid");
                break;
            case "Girboks": newComponent = new Gearbox(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), "Diesel");
                break;
            default: Dialogs.showErrorDialog("Legg til komponent", "Fant ikke komponenten", "Prøv igjen");
        }
        Context.getInstance().getRegisterComponent().setComponentsList(newComponent);
    }

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Main.setRoot("admincomponent");
    }

    @FXML
    void btnComplete(ActionEvent event) {
        //Dette er for å teste
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
        Gearbox gearbox = new Gearbox("Manuell",3000,"Diesel");
        Context.getInstance().getRegisterComponent().setComponentsList(gearbox);
        Gearbox gearbox2 = new Gearbox("Automat",5000,"Diesel");
        Context.getInstance().getRegisterComponent().setComponentsList(gearbox2);
        Battery battery = new Battery("Li-ion",10000,"Hybrid", "Electric");
        Context.getInstance().getRegisterComponent().setComponentsList(battery);
        FuelContainer fuelContainer = new FuelContainer("50-liter",5000,"Diesel", "Hybrid");
        Context.getInstance().getRegisterComponent().setComponentsList(fuelContainer);
        FuelContainer fuelContainer2 = new FuelContainer("100-liter",10000,"Diesel", "Hybrid");
        Context.getInstance().getRegisterComponent().setComponentsList(fuelContainer2);
    }
}
