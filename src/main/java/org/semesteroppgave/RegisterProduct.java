package org.semesteroppgave;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.semesteroppgave.carmodel.Car;

public class RegisterProduct {
    private ObservableList<Car> carList = FXCollections.observableArrayList();

    public ObservableList<Car> getCarList(){
        return this.carList;
    }

    public void setCarList(Car car){
        carList.add(car);
    }
}
