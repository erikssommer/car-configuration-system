package org.semesteroppgave.models.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.data.customizations.Custom;
import org.semesteroppgave.models.data.productmodels.Diesel;
import org.semesteroppgave.models.data.productmodels.Electric;
import org.semesteroppgave.models.data.productmodels.Hybrid;
import org.semesteroppgave.models.data.productmodels.Product;
import org.semesteroppgave.models.exceptions.DuplicateException;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.text.DecimalFormat;
import java.util.stream.Collectors;

/**
 * Product creation model on the user's site
 * Is connected to UserBuildProductController
 */

public class UserCreateProduct {
    private final ObservableList<String> modelComponentsList = FXCollections.observableArrayList(); //Component name list
    private final ObservableList<Component> chooseComponentList = FXCollections.observableArrayList(); //Component selection list
    private final ObservableList<String> modelChoice = FXCollections.observableArrayList(); //List of comboBox selections
    private final TableView<String> tableViewComponent;
    private final TableView<Component> tableViewVersion;
    private final ComboBox<String> cbModel;
    private final Label lblMessage;
    private final TextField txtTotalPrice;

    private double livePrice;
    private double[] livePriceList; //List that holds the prices
    private Component[] productComponants; //List that holds selected components
    private Custom[] productCustomization; //List that holds selected customizations

    public UserCreateProduct(TableView<String> tableViewComponent, TableView<Component> tableViewVersion,
                             ComboBox<String> cbModel, Label lblMessage, TextField txtTotalPrice) {
        this.tableViewComponent = tableViewComponent;
        this.tableViewVersion = tableViewVersion;
        this.cbModel = cbModel;
        this.lblMessage = lblMessage;
        this.txtTotalPrice = txtTotalPrice;
    }

    public void createNewProduct(String model) {
        modelComponentsList.clear();
        //Finds components for the selected model
        ApplicationData.getInstance().getRegisterComponent().getComponentList()
                .forEach(modelList -> modelList.getModel()
                        .stream().filter(componentModel -> componentModel.equals(model))
                        .map(componentModel -> modelList.getComponent())
                        .forEachOrdered(modelComponentsList::add));

        setLabelText("You can now select components for your \n" + model.toLowerCase() + " car");

        //Resets the lists when selecting a new model type
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
        //Uses streams with .distinct() to remove duplicates and adds them to the component type selection list
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
                .stream().filter(component -> component.getComponent().equals(selectedComponent))
                .forEachOrdered(chooseComponentList::add);

        tableViewVersion.setItems(chooseComponentList);

        //When a user hovers over a component, the description will appear in a window
        tableViewVersion.setRowFactory(tableView -> {
            Tooltip tooltip = new Tooltip();
            tooltip.setShowDelay(new Duration(500));
            final TableRow<Component> row = new TableRow<>();

            row.hoverProperty().addListener((observable) -> {
                final Component component = row.getItem();

                if (row.isHover() && component != null) {
                    tooltip.setText("Description: " + component.getDescription());
                    tableViewVersion.setTooltip(tooltip);
                }
            });

            return row;
        });

        tableViewVersion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                addToProduct(newSelection);
            }
        });
    }

    //Inserts selected components into the list of each component's index
    private void addToProduct(Component selectedComponent) {
        productComponants[selectedComponent.getIndex()] = selectedComponent;
        livePriceList[selectedComponent.getIndex() + 1] = selectedComponent.getPrice();

        addToPrice();
        setLabelText("You have selected new " + selectedComponent.getComponent().toLowerCase());
    }

    //Method of selecting customizations
    public void customization(CheckBox checkBox, Custom mode) {

        if (checkBox.isSelected() && productCustomization[mode.getIndex()] == null) {
            setLabelText("You added " + mode.getCustomProperty().toLowerCase());
            productCustomization[mode.getIndex()] = mode;
            livePriceList[mode.getIndex() + 9] = productCustomization[mode.getIndex()].getPrice();
        }

        if (!checkBox.isSelected() && productCustomization[mode.getIndex()] != null) {
            setLabelText("You have removed " + mode.getCustomProperty().toLowerCase());
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
                throw new DuplicateException("The product is already registered");
            }
        }
    }

    //Method that creates products. Use of builder pattern
    public void finishedProduct() throws IllegalArgumentException {

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
            Dialogs.showConfirmationDialog("You want to create a " + cbModel.getValue().toLowerCase() + " car",
                    "Are you sure about this?",
                    response -> {
                        if (response == ButtonType.OK) {
                            ApplicationData.getInstance().getRegisterProduct().setUserProductList(finalProduct);
                            lblMessage.setText("The product has been created\nClick on 'configured cars' for overview");
                        }
                    });
        } else {
            Dialogs.showErrorDialog("Oups!", "You must choose the model",
                    "Then choose components for the car");
        }
    }

    //Inserts model type selection in combobox
    public void loadChoice() {
        modelChoice.removeAll();
        modelChoice.addAll("Elektrisk", "Diesel", "Hybrid");
        cbModel.getItems().addAll(modelChoice);
        cbModel.setPromptText("Choose model");
    }
}

