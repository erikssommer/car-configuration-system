package org.semesteroppgave;

import javafx.scene.control.*;
import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.carcustomization.Autopilot;
import org.semesteroppgave.carcustomization.Gps;
import org.semesteroppgave.carcustomization.Sunroof;
import org.semesteroppgave.carcustomization.Towbar;
import org.semesteroppgave.carmodel.Car;
import org.semesteroppgave.carmodel.Diesel;
import org.semesteroppgave.carmodel.Electric;
import org.semesteroppgave.carmodel.Hybrid;
import org.semesteroppgave.gui.Dialogs;

public class UserCreateCar {

    private TableView<Component> tableViewComponent;
    private TableView<Component> tableViewVersion;
    private ComboBox<String> cbModel;
    private Label lblMessage;
    private TextField txtTotalPrice;

    private Battery battery;
    private FuelContainer fuelContainer;
    private Gearbox gearbox;
    private Motor motor;
    private Rim rim;
    private SeatCover seatCover;
    private Spoiler spoiler;
    private Tires tires;

    private Autopilot autopilot;
    private Gps gps;
    private Sunroof sunroof;
    private Towbar towbar;

    private double livePrice;
    private double [] livePriceList;

    public UserCreateCar(TableView<Component> tableViewComponent, TableView<Component> tableViewVersion, ComboBox<String> cbModel, Label lblMessage, TextField txtTotalPrice){
        this.tableViewComponent = tableViewComponent;
        this.tableViewVersion = tableViewVersion;
        this.cbModel = cbModel;
        this.lblMessage = lblMessage;
        this.txtTotalPrice = txtTotalPrice;
    }

    public void createNewCar(String model1, String model2){
        Context.getInstance().getRegisterComponent().clearModelComponentsList();
        for (Component model : Context.getInstance().getRegisterComponent().getComponentsList()){
            for (String componentModel : model.getModel()){
                if (componentModel.equals(model1) || componentModel.equals(model2)){
                    if (!redundance(model.getComponent())){
                        Context.getInstance().getRegisterComponent().setModelComponentsList(model);
                        lblMessage.setText("Du kan nå velge komponenter til din \n"+model1+" bil");
                    }
                }
            }
        }

        livePriceList = new double[13];

        if ("Electric".equals(model1)) {
            livePriceList[0] = 1_200_000;
        }
        if ("Diesel".equals(model1)){
            livePriceList[0] = 400_000;
        }
        if ("Hybrid".equals(model1)){
            livePriceList[0] = 850_000;
        }
        updateLivePrice();
        tableViewComponent.setItems(Context.getInstance().getRegisterComponent().getModelComponentsList());

        tableViewComponent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null){
                showComponents(tableViewComponent.getSelectionModel().getSelectedItem().getComponent());
            }
        });
    }

    public boolean redundance(String componentModel){
        boolean redundance = false;
        for (Component component : Context.getInstance().getRegisterComponent().getModelComponentsList()){
            redundance = componentModel.equals(component.getComponent());
        }
        return redundance;
    }

    public void showComponents(String selectedComponent){
        //Teller som forsikrer at addToCar metoden bare kjøres én gang for hver event
        final int[] counter = {0};
        Context.getInstance().getRegisterComponent().clearChooseComponentList();
        for (Component component : Context.getInstance().getRegisterComponent().getComponentsList()){
            if (component.getComponent().equals(selectedComponent)){
                Context.getInstance().getRegisterComponent().setChooseComponentList(component);
            }
        }
        tableViewVersion.setItems(Context.getInstance().getRegisterComponent().getChooseComponentList());
        tableViewVersion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && counter[0] == 0){
                addToCar(newSelection.getComponent());
                counter[0]++;
            }
        });
    }

    public void addToCar(String selectedComponent){
        System.out.println("Teller");
        //System.out.println(selectedComponent);
        switch (selectedComponent){
            case "Motor": motor = (Motor) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[1] = motor.getPrice();
                break;
            case "Felg": rim = (Rim) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[2] = rim.getPrice();
                break;
            case "Setetrekk": seatCover = (SeatCover) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[3] = seatCover.getPrice();
                break;
            case "Spoiler": spoiler = (Spoiler) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[4] = spoiler.getPrice();
                break;
            case "Dekk": tires = (Tires) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[5] = tires.getPrice();
                break;
            case "Batteri": battery = (Battery) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[6] = battery.getPrice();
                break;
            case "Tank": fuelContainer = (FuelContainer) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[7] = fuelContainer.getPrice();
                break;
            case "Girboks": gearbox = (Gearbox) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[8] = gearbox.getPrice();
                break;
            default: Dialogs.showErrorDialog("Legg til komponent", "Fant ikke komponenten", "Prøv igjen");
        }
        addToPrice();
        lblMessage.setText("Du har opprettet ny "+selectedComponent.toLowerCase());
    }

    public void customization(CheckBox cbAutopilot, CheckBox cbTowbar, CheckBox cbSunroof, CheckBox cbGps){

        if (cbAutopilot.isSelected() && autopilot == null){
            System.out.println("Autopilot");
            autopilot = new Autopilot();
            livePriceList[9] = autopilot.getPrice();
        }

        if (!cbAutopilot.isSelected() && autopilot != null){
            livePriceList[9] = 0;
            autopilot = null;
        }

        if (cbTowbar.isSelected() && towbar == null){
            System.out.println("Towbar");
            towbar = new Towbar();
            livePriceList[10] = towbar.getPrice();
        }

        if (!cbTowbar.isSelected() && towbar != null){
            livePriceList[10] = 0;
            towbar = null;
        }

        if (cbSunroof.isSelected() && sunroof == null){
            System.out.println("Sunroof");
            sunroof = new Sunroof();
            livePriceList[11] = sunroof.getPrice();
        }

        if (!cbSunroof.isSelected() && sunroof != null){
            livePriceList[11] = 0;
            sunroof = null;
        }

        if (cbGps.isSelected() && gps == null){
            System.out.println("Gps");
            gps = new Gps();
            livePriceList[12] = gps.getPrice();
        }

        if (!cbGps.isSelected() && gps != null){
            livePriceList[12] = 0;
            gps = null;
        }

        addToPrice();
    }

    public void addToPrice(){

        livePrice = 0;
        for (double pris : livePriceList){
            livePrice += pris;
        }
        updateLivePrice();
    }

    public void updateLivePrice(){
        txtTotalPrice.setText(String.valueOf(livePrice));
    }

    public void finishedCar (){

        if (cbModel.getValue() != null){
            try {
                Car product = null;
                switch (cbModel.getValue()){
                    case "Elektrisk": product = new Electric(motor, rim, seatCover, spoiler, tires, gps, sunroof, towbar, battery, autopilot);
                        break;
                    case "Diesel": product = new Diesel(motor, rim, seatCover, spoiler, tires, gps, sunroof, towbar, fuelContainer, gearbox);
                        break;
                    case "Hybrid": product = new Hybrid(motor, rim, seatCover, spoiler, tires, gps, sunroof, towbar, battery, fuelContainer);
                        break;
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Bekreft");
                alert.setHeaderText("Du ønsker å opprette en "+ cbModel.getValue().toLowerCase()+ " bil");
                alert.setContentText("Er du sikker på dette?");
                Car finalProduct = product;
                alert.showAndWait().ifPresent(response -> {
                    if(response == ButtonType.OK){
                        Dialogs.showSuccessDialog("Gjennomført", "Du har nå opprettet din komfigurasjon", "Trykk på 'vis konfig' for å se en oversikt");
                        Context.getInstance().getRegisterProduct().setMyCarList(finalProduct);
                    }
                });

            }catch (NullPointerException e){
                Dialogs.showErrorDialog("Oups", "Feil i oppretting av komponenter", e.getMessage());
            }

        }else {
            Dialogs.showErrorDialog("Oups!", "Du må velge modell", "Deretter velge komponenter til bilen");
        }
    }
}
