package org.semesteroppgave;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.semesteroppgave.carmodel.Car;

public class RegisterProduct {
    //Listen carList er alle tidligere konfigurerte biler
    //Listen myCarList er min konfigurerte bil
    //Objektet selectedCar er den bilen som skal vises mer informasjon om
    private ObservableList<Car> carList = FXCollections.observableArrayList();
    private ObservableList<Car> myCarList = FXCollections.observableArrayList();
    private Car selectedCar;

    public ObservableList<Car> getCarList(){
        return this.carList;
    }

    public void setCarList(Car car){
        carList.add(car);
    }

    public ObservableList<Car> getMyCarList(){
        return this.myCarList;
    }

    public void setMyCarList(Car car){
        myCarList.add(car);
    }

    public Car getSelectedCar(){
        return this.selectedCar;
    }

    public void setSelectedCar(Car car){
        this.selectedCar = car;
    }
}