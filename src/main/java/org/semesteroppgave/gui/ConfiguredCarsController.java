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

    @FXML
    private TableColumn<Car, String> txtMotorColumnMy, txtRimColumnMy, txtSeatcoverColumnMy, txtSpoilerColumnMy, txtTireColumnMy;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableColum(txtMotorColumn, txtRimColumn, txtSeatcoverColumn, txtSpoilerColumn, txtTireColumn, txtPriceColumn);
        tableViewConfigs.setItems(Context.getInstance().getRegisterProduct().getCarList());

        setTableColum(txtMotorColumnMy, txtRimColumnMy, txtSeatcoverColumnMy, txtSpoilerColumnMy, txtTireColumnMy, txtPriceColumnMy);
        tableViewMyConfig.setItems(Context.getInstance().getRegisterProduct().getMyCarList());

    }

    private void setTableColum(TableColumn<Car, String> txtMotorColumn, TableColumn<Car, String> txtRimColumn, TableColumn<Car, String> txtSeatcoverColumn, TableColumn<Car, String> txtSpoilerColumn, TableColumn<Car, String> txtTireColumn, TableColumn<Car, Double> txtPriceColumn) {
        txtMotorColumn.setCellValueFactory(new PropertyValueFactory<>("motor"));
        txtRimColumn.setCellValueFactory(new PropertyValueFactory<>("rim"));
        txtSeatcoverColumn.setCellValueFactory(new PropertyValueFactory<>("seatCover"));
        txtSpoilerColumn.setCellValueFactory(new PropertyValueFactory<>("spoiler"));
        txtTireColumn.setCellValueFactory(new PropertyValueFactory<>("tires"));
        txtPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        txtPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Main.setRoot("userbuildcar");
    }

    @FXML
    void btnExport(ActionEvent event) {

    }

    @FXML
    void btnAddToConfigs(ActionEvent event) {
        if (tableViewConfigs.getSelectionModel().getSelectedItem() != null){
            Dialogs.showErrorDialog("Oups", "Markeringi feil liste", "Denne konfigurasjonen ligger allerede i listen");
        } else if (tableViewMyConfig.getSelectionModel().getSelectedItem() != null){
            Context.getInstance().getRegisterProduct().setCarList(tableViewMyConfig.getSelectionModel().getSelectedItem());
            Context.getInstance().getRegisterProduct().getMyCarList().remove(tableViewMyConfig.getSelectionModel().getSelectedItem());

        }else {
            Dialogs.showErrorDialog("Oups", "Du må markere produktet ditt først!", "Prøv igjen etter å ha valgt din konfigurasjon");
        }
    }

    @FXML
    void btnMoreInfo(ActionEvent event) {
        chooseTable(tableViewConfigs, tableViewMyConfig);
    }

    private void chooseTable(TableView<Car> tableViewMyConfig, TableView<Car> tableViewConfigs) {
        if (tableViewMyConfig.getSelectionModel().getSelectedItem() != null || tableViewConfigs.getSelectionModel().getSelectedItem() != null){
            if (tableViewMyConfig.getSelectionModel().getSelectedItem() != null && tableViewConfigs.getSelectionModel().getSelectedItem() == null){
                Context.getInstance().getRegisterProduct().setSelectedCar(tableViewMyConfig.getSelectionModel().getSelectedItem());
            }else {
                Context.getInstance().getRegisterProduct().setSelectedCar(tableViewConfigs.getSelectionModel().getSelectedItem());
            }
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
