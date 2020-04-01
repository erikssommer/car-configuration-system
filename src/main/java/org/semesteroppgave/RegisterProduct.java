package org.semesteroppgave;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.semesteroppgave.carmodel.Car;

public class RegisterProduct {
    private ObservableList<Car> carList = FXCollections.observableArrayList();
    private Car selectedCar;

    public ObservableList<Car> getCarList(){
        return this.carList;
    }

    public void setCarList(Car car){
        carList.add(car);
    }

    //Finner den bilen som skal vises mer informasjon om
    public Car getSelectedCar(){
        return this.selectedCar;
    }

    public void setSelectedCar(Car car){
        this.selectedCar = car;
    }
}
