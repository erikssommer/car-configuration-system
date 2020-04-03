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
        try {
            switch (Context.getInstance().getRegisterComponent().getNewComponent()){
                case "Motor": newComponent = new Motor(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), txtDescription.getText());
                    break;
                case "Felg": newComponent = new Rim(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), txtDescription.getText());
                    break;
                case "Setetrekk": newComponent = new SeatCover(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), txtDescription.getText());
                    break;
                case "Spoiler": newComponent = new Spoiler(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), txtDescription.getText());
                    break;
                case  "Dekk": newComponent = new Tires(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), txtDescription.getText());
                    break;
                case "Batteri": newComponent = new Battery(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), txtDescription.getText());
                    break;
                case "Tank": newComponent = new FuelContainer(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), txtDescription.getText());
                    break;
                case "Girboks": newComponent = new Gearbox(txtVersion.getText(), Double.parseDouble(txtPrice.getText()), txtDescription.getText());
                    break;
                default: Dialogs.showErrorDialog("Legg til komponent", "Fant ikke komponenten", "Prøv igjen");
            }
            Context.getInstance().getRegisterComponent().setComponentsList(newComponent);
        }catch (NullPointerException | NumberFormatException e){
            Dialogs.showErrorDialog("Oups!", "Du har glemt noe", e.getMessage());
        }
    }

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Main.setRoot("admincomponent");
    }

    @FXML
    void btnComplete(ActionEvent event) {
        //Dette er for å teste
        Motor motor = new Motor("V12",12300,"Denne V12 motoren er slitesterk og har en veldig høy ytelse");
        Context.getInstance().getRegisterComponent().setComponentsList(motor);
        Motor motor2 = new Motor("V8",1230, "Denne V8 motoren er lett å vedlikeholde og lager lite lyd");
        Context.getInstance().getRegisterComponent().setComponentsList(motor2);
        Rim rim = new Rim("Feit",3900,"Disse felgene er laget av aluminium som gir en fin glans i solen");
        Context.getInstance().getRegisterComponent().setComponentsList(rim);
        SeatCover seatCover = new SeatCover("Mykt",123,"Dette setetrekket er veldig komfortabelt under lange kjøreturer");
        Context.getInstance().getRegisterComponent().setComponentsList(seatCover);
        SeatCover seatCover2 = new SeatCover("Sport",12300,"Dette setetrekket er for deg som er ute etter fart. Mindre behagelig over tid");
        Context.getInstance().getRegisterComponent().setComponentsList(seatCover2);
        Spoiler spoiler = new Spoiler("Høy",1000,"Denne spoileren gir ekstra fart ettersom den er meget høy");
        Context.getInstance().getRegisterComponent().setComponentsList(spoiler);
        //Denne bruker jeg til å teste redundance
        FuelContainer fuelContainer3 = new FuelContainer("100-liter",2000,"Denne tanken er giga stor og har god kondens");
        Context.getInstance().getRegisterComponent().setComponentsList(fuelContainer3);

        Spoiler spoiler2 = new Spoiler("Lav",500,"Denne spoileren gir mindre fart men god luftmotstand");
        Context.getInstance().getRegisterComponent().setComponentsList(spoiler2);
        Tires tires = new Tires("Vinter",1000,"Disse vinterdekkene er gode på alle typer snø og is");
        Context.getInstance().getRegisterComponent().setComponentsList(tires);
        Tires tires2 = new Tires("Sommer",1500,"Disse sommerdekkene er veldig slitesterke");
        Context.getInstance().getRegisterComponent().setComponentsList(tires2);
        Tires tires3 = new Tires("Alround",2000,"Disse dekkene kan du bruke både sommer og vinter");
        Context.getInstance().getRegisterComponent().setComponentsList(tires3);
        Gearbox gearbox = new Gearbox("Manuell",3000,"Manuell girboks er billigere enn automat, og gir deg mer kontroll");
        Context.getInstance().getRegisterComponent().setComponentsList(gearbox);
        Gearbox gearbox2 = new Gearbox("Automat",5000,"Automat girboks er dyrere enn manuell, men er mer komportabelt");
        Context.getInstance().getRegisterComponent().setComponentsList(gearbox2);
        Battery battery = new Battery("Li-ion",10000,"Li-ion batteri er miljøvennlige og har lang rekkevidde");
        Context.getInstance().getRegisterComponent().setComponentsList(battery);
        FuelContainer fuelContainer = new FuelContainer("50-liter",5000,"Denne tanken er ikke så stor, men er billig");
        Context.getInstance().getRegisterComponent().setComponentsList(fuelContainer);
        FuelContainer fuelContainer2 = new FuelContainer("100-liter",10000,"Denne tanken er stor og har god kondens");
        Context.getInstance().getRegisterComponent().setComponentsList(fuelContainer2);
    }
}
