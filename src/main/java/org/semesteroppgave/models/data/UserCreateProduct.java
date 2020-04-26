package org.semesteroppgave.models.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.data.customizations.Autopilot;
import org.semesteroppgave.models.data.customizations.Gps;
import org.semesteroppgave.models.data.customizations.Sunroof;
import org.semesteroppgave.models.data.customizations.Towbar;
import org.semesteroppgave.models.data.productmodels.Diesel;
import org.semesteroppgave.models.data.productmodels.Electric;
import org.semesteroppgave.models.data.productmodels.Hybrid;
import org.semesteroppgave.models.data.productmodels.Product;
import org.semesteroppgave.models.exceptions.DuplicateException;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.text.DecimalFormat;
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

    public void createNewProduct(String model) {
        modelComponentsList.clear();

        for (Component modelList : ApplicationData.getInstance().getRegisterComponent().getComponentList()) {
            for (String componentModel : modelList.getModel()) {
                if (componentModel.equals(model)) {
                    modelComponentsList.add(modelList.getComponent());
                }
            }
        }

        setLabelText("Du kan nå velge komponenter til din \n" + model.toLowerCase() + " bil");

        livePriceList = new double[13];

        if (model.equals("Elektrisk")) {
            livePriceList[0] = 1_200_000;
        }
        if (model.equals("Diesel")) {
            livePriceList[0] = 400_000;
        }
        if (model.equals("Hybrid")) {
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

        ApplicationData.getInstance().getRegisterComponent().getComponentList()
                .stream()
                .filter(component -> component.getComponent().equals(selectedComponent))
                .forEachOrdered(chooseComponentList::add);

        tableViewVersion.setItems(chooseComponentList);

        tableViewVersion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                addToProduct(newSelection.getComponent());
            }
        });
    }

    private void addToProduct(String selectedComponent) {

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
        for (double price : livePriceList) {
            livePrice += price;
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
        for (Product userProduct : ApplicationData.getInstance().getRegisterProduct().getUserProductList()) {
            if (userProduct.equals(product)) {
                lblMessage.setText("");
                throw new DuplicateException("Produktet er registrert fra før");
            }
        }
    }

    public void finishedProduct() throws NullPointerException, IllegalArgumentException {

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
                    ApplicationData.getInstance().getRegisterProduct().setUserProductList(finalProduct);
                    lblMessage.setText("Produktet er opprettet\nTrykk på 'konfgurerte biler' for oversikt");
                }
            });
        } else {
            Dialogs.showErrorDialog("Oups!", "Du må velge modell", "Deretter velge komponenter til bilen");
        }
    }
}
