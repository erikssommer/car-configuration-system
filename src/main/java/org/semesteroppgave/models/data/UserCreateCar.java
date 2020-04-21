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
import org.semesteroppgave.models.data.carmodel.*;
import org.semesteroppgave.models.exceptions.*;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.text.DecimalFormat;

public class UserCreateCar {

    private final ObservableList<Component> modelComponentsList = FXCollections.observableArrayList();
    private final ObservableList<Component> chooseComponentList = FXCollections.observableArrayList();
    private final TableView<Component> tableViewComponent;
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

    public UserCreateCar(TableView<Component> tableViewComponent, TableView<Component> tableViewVersion, ComboBox<String> cbModel, Label lblMessage, TextField txtTotalPrice) {
        this.tableViewComponent = tableViewComponent;
        this.tableViewVersion = tableViewVersion;
        this.cbModel = cbModel;
        this.lblMessage = lblMessage;
        this.txtTotalPrice = txtTotalPrice;
    }

    public void createNewCar(String model1, String model2) {
        modelComponentsList.clear();
        for (Component model : ApplicationData.getInstance().getRegisterComponent().getComponentsList()) {
            for (String componentModel : model.getModel()) {
                if (componentModel.equals(model1) || componentModel.equals(model2)) {
                    if (!redundancy(model.getComponent())) {
                        modelComponentsList.add(model);
                        setLabelText("Du kan nå velge komponenter til din \n" + model1.toLowerCase() + " bil");
                    }
                }
            }
        }

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
        tableViewComponent.setItems(modelComponentsList);

        tableViewComponent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showComponents(tableViewComponent.getSelectionModel().getSelectedItem().getComponent());
            }
        });
    }

    private boolean redundancy(String componentModel) {
        for (Component component : modelComponentsList) {
            if (component.getComponent().equals(componentModel)) {
                return true;
            }
        }
        return false;
    }

    private void showComponents(String selectedComponent) {
        chooseComponentList.clear();
        for (Component component : ApplicationData.getInstance().getRegisterComponent().getComponentsList()) {
            if (component.getComponent().equals(selectedComponent)) {
                chooseComponentList.add(component);
            }
        }
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

    private void duplicateProduct(Car product) throws DuplicateException {
        for (Car car : ApplicationData.getInstance().getRegisterProduct().getMyCarList()) {
            if (car.equals(product)) {
                throw new DuplicateException("Produktet er registrert fra før");
            }
        }
    }

    public void finishedCar() throws NullPointerException, IllegalArgumentException {

        if (cbModel.getValue() != null) {
            Car product = null;
            switch (cbModel.getValue()) {
                case "Elektrisk":
                    product = new Electric(motor, rim, seatCover, spoiler, tires, gps, sunroof, towbar, battery, autopilot);
                    break;
                case "Diesel":
                    product = new Diesel(motor, rim, seatCover, spoiler, tires, gps, sunroof, towbar, fuelContainer, gearbox);
                    break;
                case "Hybrid":
                    product = new Hybrid(motor, rim, seatCover, spoiler, tires, gps, sunroof, towbar, battery, fuelContainer);
                    break;
            }

            Car finalProduct = product;
            duplicateProduct(finalProduct);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bekreft");
            alert.setHeaderText("Du ønsker å opprette en " + cbModel.getValue().toLowerCase() + " bil");
            alert.setContentText("Er du sikker på dette?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Dialogs.showSuccessDialog("Gjennomført", "Du har nå opprettet din komfigurasjon", "Trykk på 'vis konfig' for å se en oversikt");
                    ApplicationData.getInstance().getRegisterProduct().setMyCarList(finalProduct);
                }
            });
        } else {
            Dialogs.showErrorDialog("Oups!", "Du må velge modell", "Deretter velge komponenter til bilen");
        }
    }
}
