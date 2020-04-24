package org.semesteroppgave.models.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.carcomponents.*;
import org.semesteroppgave.models.data.carcustomization.Autopilot;
import org.semesteroppgave.models.data.carcustomization.Gps;
import org.semesteroppgave.models.data.carcustomization.Sunroof;
import org.semesteroppgave.models.data.carcustomization.Towbar;
import org.semesteroppgave.models.data.carmodel.Diesel;
import org.semesteroppgave.models.data.carmodel.Electric;
import org.semesteroppgave.models.data.carmodel.Hybrid;
import org.semesteroppgave.models.data.carmodel.Product;
import org.semesteroppgave.models.exceptions.DuplicateException;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Modellen for opprettelse av produkt på brukeren sin side.
 * Er koblet til UserBuildProductController
 */

public class UserCreateProduct {
    private final ObservableList<String> modelComponentsList = FXCollections.observableArrayList(); //Liste for komponentnavn
    private final ObservableList<Component> chooseComponentList = FXCollections.observableArrayList(); //Liste for valg av komponent
    private final TableView<String> tableViewComponent;
    private final TableView<Component> tableViewVersion;
    private final ComboBox<String> cbModel;
    private final Label lblMessage;
    private final TextField txtTotalPrice;

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
    private double[] livePriceList;

    public UserCreateProduct(TableView<String> tableViewComponent, TableView<Component> tableViewVersion, ComboBox<String> cbModel, Label lblMessage, TextField txtTotalPrice) {
        this.tableViewComponent = tableViewComponent;
        this.tableViewVersion = tableViewVersion;
        this.cbModel = cbModel;
        this.lblMessage = lblMessage;
        this.txtTotalPrice = txtTotalPrice;
    }

    public void createNewCar(String model1, String model2) {
        modelComponentsList.clear();

        ApplicationData.getInstance().getRegisterComponent().getComponentsList()
                .forEach(model -> Arrays.stream(model.getModel())
                        .filter(componentModel -> componentModel.equals(model1) || componentModel.equals(model2)).forEach(componentModel -> {
                            modelComponentsList.add(model.getComponent());
                            setLabelText("Du kan nå velge komponenter til din \n" + model1.toLowerCase() + " bil");
                        }));

        livePriceList = new double[13];

        if ("Elektrisk".equals(model1)) {
            livePriceList[0] = 1_200_000;
        }
        if ("Diesel".equals(model1)) {
            livePriceList[0] = 400_000;
        }
        if ("Hybrid".equals(model1)) {
            livePriceList[0] = 850_000;
        }

        updateLivePrice();
        //Bruker streams med .distinct() for å fjerne duplikater og legger de til listen for valg av komponenttype
        tableViewComponent.setItems(modelComponentsList.stream().distinct()
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));

        tableViewComponent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showComponents(tableViewComponent.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void showComponents(String selectedComponent) {
        chooseComponentList.clear();

        ApplicationData.getInstance().getRegisterComponent().getComponentsList()
                .stream()
                .filter(component -> component.getComponent().equals(selectedComponent))
                .forEachOrdered(chooseComponentList::add);

        tableViewVersion.setItems(chooseComponentList);

        tableViewVersion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                addToCar(newSelection.getComponent());
            }
        });
    }

    private void addToCar(String selectedComponent) {

        switch (selectedComponent) {
            case "Motor":
                motor = (Motor) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[1] = motor.getPrice();
                break;
            case "Felg":
                rim = (Rim) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[2] = rim.getPrice();
                break;
            case "Setetrekk":
                seatCover = (SeatCover) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[3] = seatCover.getPrice();
                break;
            case "Spoiler":
                spoiler = (Spoiler) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[4] = spoiler.getPrice();
                break;
            case "Dekk":
                tires = (Tires) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[5] = tires.getPrice();
                break;
            case "Batteri":
                battery = (Battery) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[6] = battery.getPrice();
                break;
            case "Tank":
                fuelContainer = (FuelContainer) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[7] = fuelContainer.getPrice();
                break;
            case "Girboks":
                gearbox = (Gearbox) tableViewVersion.getSelectionModel().getSelectedItem();
                livePriceList[8] = gearbox.getPrice();
                break;
            default:
                Dialogs.showErrorDialog("Legg til komponent", "Fant ikke komponenten", "Prøv igjen");
        }
        addToPrice();
        setLabelText("Du har valgt ny " + selectedComponent.toLowerCase());
    }

    public void customization(CheckBox cbAutopilot, CheckBox cbTowbar, CheckBox cbSunroof, CheckBox cbGps) {

        if (cbAutopilot.isSelected() && autopilot == null) {
            setLabelText("Du har lagt til autopilot");
            autopilot = new Autopilot();
            livePriceList[9] = autopilot.getPrice();
        }

        if (!cbAutopilot.isSelected() && autopilot != null) {
            setLabelText("Du har fjernet autopilot");
            livePriceList[9] = 0;
            autopilot = null;
        }

        if (cbTowbar.isSelected() && towbar == null) {
            setLabelText("Du har lagt til hengerfeste");
            towbar = new Towbar();
            livePriceList[10] = towbar.getPrice();
        }

        if (!cbTowbar.isSelected() && towbar != null) {
            setLabelText("Du har fjernet hengerfeste");
            livePriceList[10] = 0;
            towbar = null;
        }

        if (cbSunroof.isSelected() && sunroof == null) {
            setLabelText("Du har lagt til soltak");
            sunroof = new Sunroof();
            livePriceList[11] = sunroof.getPrice();
        }

        if (!cbSunroof.isSelected() && sunroof != null) {
            setLabelText("Du har fjernet soltak");
            livePriceList[11] = 0;
            sunroof = null;
        }

        if (cbGps.isSelected() && gps == null) {
            setLabelText("Du har lagt til gps-system");
            gps = new Gps();
            livePriceList[12] = gps.getPrice();
        }

        if (!cbGps.isSelected() && gps != null) {
            setLabelText("Du har fjernet gps-system");
            livePriceList[12] = 0;
            gps = null;
        }

        addToPrice();
    }

    private void addToPrice() {

        livePrice = 0;
        for (double pris : livePriceList) {
            livePrice += pris;
        }
        updateLivePrice();
    }

    public void updateLivePrice() {
        DecimalFormat df = new DecimalFormat("###,###,###.###");
        txtTotalPrice.setText(df.format(livePrice) + "kr");
    }

    private void setLabelText(String message) {
        lblMessage.setText(message);
    }

    private void duplicateProduct(Product product) throws DuplicateException {
        for (Product car : ApplicationData.getInstance().getRegisterProduct().getMyProductList()) {
            if (car.equals(product)) {
                throw new DuplicateException("Produktet er registrert fra før");
            }
        }
    }

    public void finishedCar() throws NullPointerException, IllegalArgumentException {

        if (cbModel.getValue() != null) {
            Product product = null;
            switch (cbModel.getValue()) {
                case "Elektrisk":
                    product = new Electric.Builder("Elektrisk", 1_200_000)
                            .selectedMotor(motor)
                            .selectedRim(rim)
                            .selectedSeatcover(seatCover)
                            .selectedSpoiler(spoiler)
                            .selectedTires(tires)
                            .selectedBattery(battery)
                            .withGps(gps)
                            .withSunroof(sunroof)
                            .withTowbar(towbar)
                            .withAutopilot(autopilot)
                            .build();
                    break;
                case "Diesel":
                    product = new Diesel.Builder("Diesel", 400_000)
                            .selectedMotor(motor)
                            .selectedRim(rim)
                            .selectedSeatcover(seatCover)
                            .selectedSpoiler(spoiler)
                            .selectedTires(tires)
                            .selectedFuelContainer(fuelContainer)
                            .selectedGearbox(gearbox)
                            .withGps(gps)
                            .withSunroof(sunroof)
                            .withTowbar(towbar)
                            .build();
                    break;
                case "Hybrid":
                    product = new Hybrid.Builder("Hybrid", 850_000)
                            .selectedMotor(motor)
                            .selectedRim(rim)
                            .selectedSeatcover(seatCover)
                            .selectedSpoiler(spoiler)
                            .selectedTires(tires)
                            .selectedBattery(battery)
                            .selectedFuelContainer(fuelContainer)
                            .withGps(gps)
                            .withSunroof(sunroof)
                            .withTowbar(towbar)
                            .build();
                    break;
            }

            Product finalProduct = product;
            duplicateProduct(finalProduct);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bekreft");
            alert.setHeaderText("Du ønsker å opprette en " + cbModel.getValue().toLowerCase() + " bil");
            alert.setContentText("Er du sikker på dette?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Dialogs.showSuccessDialog("Gjennomført", "Du har nå opprettet din komfigurasjon", "Trykk på 'vis konfig' for å se en oversikt");
                    ApplicationData.getInstance().getRegisterProduct().setMyProductList(finalProduct);
                }
            });
        } else {
            Dialogs.showErrorDialog("Oups!", "Du må velge modell", "Deretter velge komponenter til bilen");
        }
    }
}
