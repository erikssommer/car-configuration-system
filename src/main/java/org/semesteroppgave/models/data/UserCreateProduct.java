package org.semesteroppgave.models.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.data.customizations.*;
import org.semesteroppgave.models.data.productmodels.Diesel;
import org.semesteroppgave.models.data.productmodels.Electric;
import org.semesteroppgave.models.data.productmodels.Hybrid;
import org.semesteroppgave.models.data.productmodels.Product;
import org.semesteroppgave.models.exceptions.DuplicateException;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.text.DecimalFormat;
import java.util.stream.Collectors;

/**
 * Modell for opprettelse av produkt på brukeren sin side.
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

    private double livePrice;
    private double[] livePriceList; //Liste som holder på prisene
    private Component[] productComponants; //Liste som holder på valgte komponenter
    private Custom[] productCustomization; //Liste som holder på valgte tilpasninger

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

        //Restarter listene ved valg av ny modeltype
        productComponants = new Component[8];
        productCustomization = new Custom[Custom.values().length];
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

        addToPrice();
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
                addToProduct(newSelection);
            }
        });
    }

    //Legger inn valgte komponenter i listen med indeksen til hver komponent
    private void addToProduct(Component selectedComponent) {
        productComponants[selectedComponent.getIndex()] = selectedComponent;
        livePriceList[selectedComponent.getIndex()+1] = selectedComponent.getPrice();

        addToPrice();
        setLabelText("Du har valgt ny " + selectedComponent.getComponent().toLowerCase());
    }

    //Metode for valg av tilpasninger
    public void customization(CheckBox checkBox, Custom mode) {

        if (checkBox.isSelected() && productCustomization[mode.getIndex()] == null) {
            setLabelText("Du har lagt til " + mode.getCustomProperty().toLowerCase());
            productCustomization[mode.getIndex()] = mode;
            livePriceList[mode.getIndex() + 9] = productCustomization[mode.getIndex()].getPrice();
        }

        if (!checkBox.isSelected() && productCustomization[mode.getIndex()] != null) {
            setLabelText("Du har fjernet " + mode.getCustomProperty().toLowerCase());
            livePriceList[mode.getIndex() + 9] = 0;
            productCustomization[mode.getIndex()] = null;
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

    //Metode som oppretter produkter. Bruk av builder-pattern
    public void finishedProduct() throws NullPointerException, IllegalArgumentException {

        if (cbModel.getValue() != null) {
            Product product = null;
            switch (cbModel.getValue()) {
                case "Elektrisk":
                    product = new Electric.Builder("Elektrisk", 1_200_000)
                            .selectedMotor((Motor) productComponants[0])
                            .selectedRim((Rim) productComponants[1])
                            .selectedSeatcover((SeatCover) productComponants[2])
                            .selectedSpoiler((Spoiler) productComponants[3])
                            .selectedTires((Tires) productComponants[4])
                            .selectedBattery((Battery) productComponants[5])
                            .withGps(productCustomization[0])
                            .withSunroof(productCustomization[1])
                            .withTowbar(productCustomization[2])
                            .withAutopilot(productCustomization[3])
                            .build();
                    break;
                case "Diesel":
                    product = new Diesel.Builder("Diesel", 400_000)
                            .selectedMotor((Motor) productComponants[0])
                            .selectedRim((Rim) productComponants[1])
                            .selectedSeatcover((SeatCover) productComponants[2])
                            .selectedSpoiler((Spoiler) productComponants[3])
                            .selectedTires((Tires) productComponants[4])
                            .selectedFuelContainer((FuelContainer) productComponants[6])
                            .selectedGearbox((Gearbox) productComponants[7])
                            .withGps(productCustomization[0])
                            .withSunroof(productCustomization[1])
                            .withTowbar(productCustomization[2])
                            .build();
                    break;
                case "Hybrid":
                    product = new Hybrid.Builder("Hybrid", 850_000)
                            .selectedMotor((Motor) productComponants[0])
                            .selectedRim((Rim) productComponants[1])
                            .selectedSeatcover((SeatCover) productComponants[2])
                            .selectedSpoiler((Spoiler) productComponants[3])
                            .selectedTires((Tires) productComponants[4])
                            .selectedBattery((Battery) productComponants[5])
                            .selectedFuelContainer((FuelContainer) productComponants[6])
                            .withGps(productCustomization[0])
                            .withSunroof(productCustomization[1])
                            .withTowbar(productCustomization[2])
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

