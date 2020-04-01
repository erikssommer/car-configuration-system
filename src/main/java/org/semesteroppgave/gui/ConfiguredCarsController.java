package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.semesteroppgave.Context;
import org.semesteroppgave.Main;
import org.semesteroppgave.carcomponents.Component;
import org.semesteroppgave.carmodel.Car;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfiguredCarsController implements Initializable {
    @FXML
    private TableView<Car> tableViewConfigs;

    @FXML
    private TableView<Car> tableViewMyConfig;

    @FXML
    private TableColumn<Car, Double> txtPriceColumn;

    @FXML
    private TableColumn<Car, Double> txtPriceColumnMy;

    @FXML
    private TableColumn<Car, String> txtMotorColumn, txtRimColumn, txtSeatcoverColumn, txtSpoilerColumn, txtTireColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtMotorColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("motor"));
        txtRimColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("rim"));
        txtSeatcoverColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("seatCover"));
        txtSpoilerColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("spoiler"));
        txtTireColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("tires"));
        txtPriceColumn.setCellValueFactory(new PropertyValueFactory<Car, Double>("price"));

        txtPriceColumnMy.setCellValueFactory(new PropertyValueFactory<Car, Double>("price"));
        tableViewConfigs.setItems(Context.getInstance().getRegisterProduct().getCarList());
    }

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Main.setRoot("userbuildcar");
    }

    @FXML
    void btnExport(ActionEvent event) {

    }

    @FXML
    void btnMoreInfo(ActionEvent event) throws IOException {
        if (tableViewConfigs.getSelectionModel().getSelectedItem() != null){
            Context.getInstance().getRegisterProduct().setSelectedCar(tableViewConfigs.getSelectionModel().getSelectedItem());
            try{
                Main.setRoot("usercarinfo");
            } catch (IOException e){
                Dialogs.showErrorDialog("Oups", "Det har skjedd en feil i åpning av nytt vindu", e.getMessage());
            }
        }else {
            Dialogs.showErrorDialog("Oups", "Du må velge en bil først!", "Prøv igjen etter å ha valgt et produkt");
        }
    }
}
