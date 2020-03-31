package org.semesteroppgave;

import javafx.scene.control.*;
import org.semesteroppgave.carcomponents.*;
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

    private Battery battery = null;
    private FuelContainer fuelContainer = null;
    private Gearbox gearbox = null;
    private Motor motor = null;
    private Rim rim = null;
    private SeatCover seatCover = null;
    private Spoiler spoiler = null;
    private SteeringWheel steeringWheel = null;
    private Tires tires = null;

    private double livePrice;

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
                livePrice += motor.getPrice();
                break;
            case "Felg": rim = (Rim) tableViewVersion.getSelectionModel().getSelectedItem();
                livePrice += rim.getPrice();
                break;
            case "Setetrekk": seatCover = (SeatCover) tableViewVersion.getSelectionModel().getSelectedItem();
                livePrice += seatCover.getPrice();
                break;
            case "Ratt": steeringWheel = (SteeringWheel) tableViewVersion.getSelectionModel().getSelectedItem();
                livePrice += steeringWheel.getPrice();
                break;
            case "Spoiler": spoiler = (Spoiler) tableViewVersion.getSelectionModel().getSelectedItem();
                livePrice += spoiler.getPrice();
                break;
            case "Dekk": tires = (Tires) tableViewVersion.getSelectionModel().getSelectedItem();
                livePrice += tires.getPrice();
                break;
            case "Batteri": battery = (Battery) tableViewVersion.getSelectionModel().getSelectedItem();
                livePrice += battery.getPrice();
                break;
            case "Tank": fuelContainer = (FuelContainer) tableViewVersion.getSelectionModel().getSelectedItem();
                livePrice += fuelContainer.getPrice();
                break;
            case "Girboks": gearbox = (Gearbox) tableViewVersion.getSelectionModel().getSelectedItem();
                livePrice += gearbox.getPrice();
                break;
            default: Dialogs.showErrorDialog("Legg til komponent", "Fant ikke komponenten", "Prøv igjen");
        }
        txtTotalPrice.setText(String.valueOf(livePrice));
        lblMessage.setText("Du har opprettet ny "+selectedComponent.toLowerCase());
    }

    public Car finishedCar (){
        Car product = null;
        switch (cbModel.getValue()){
            case "Elektrisk": product = new Electric(motor, rim, seatCover, spoiler, tires, battery);
            break;
            case "Diesel": product = new Diesel(motor, rim, seatCover, spoiler, tires, fuelContainer, gearbox);
            break;
            case "Hybrid": product = new Hybrid(motor, rim, seatCover, spoiler, tires, battery, fuelContainer);
        }
        return product;
    }
}
