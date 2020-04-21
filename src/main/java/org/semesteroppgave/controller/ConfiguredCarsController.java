package org.semesteroppgave.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.data.carmodel.Car;
import org.semesteroppgave.models.filehandlers.FileHandler;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.IOException;

public class ConfiguredCarsController {
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

    public void initialize() {
        setTableColum(txtMotorColumn, txtRimColumn, txtSeatcoverColumn, txtSpoilerColumn, txtTireColumn, txtPriceColumn);
        tableViewConfigs.setItems(ApplicationData.getInstance().getRegisterProduct().getCarList());

        setTableColum(txtMotorColumnMy, txtRimColumnMy, txtSeatcoverColumnMy, txtSpoilerColumnMy, txtTireColumnMy, txtPriceColumnMy);
        tableViewMyConfig.setItems(ApplicationData.getInstance().getRegisterProduct().getMyCarList());
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
    private void btnBack(ActionEvent event) throws IOException {
        Main.setRoot("userbuildcar");
    }

    @FXML
    private void btnExport(ActionEvent event) {
        FileHandler.saveFileCsv();
    }

    @FXML
    private void btnAddToConfigs(ActionEvent event) {

        if (tableViewMyConfig.getSelectionModel().getSelectedItem() != null) {
            ApplicationData.getInstance().getRegisterProduct().setCarList(tableViewMyConfig.getSelectionModel().getSelectedItem());
            ApplicationData.getInstance().getRegisterProduct().getMyCarList().remove(tableViewMyConfig.getSelectionModel().getSelectedItem());

        } else {
            Dialogs.showErrorDialog("Oups", "Du må markere produktet ditt først!", "Prøv igjen etter å ha valgt din konfigurasjon");
        }
    }

    @FXML
    private void btnMoreInfo(ActionEvent event) {
        chooseTable(tableViewConfigs, tableViewMyConfig);
    }

    private void chooseTable(TableView<Car> tableViewMyConfig, TableView<Car> tableViewConfigs) {
        if (tableViewMyConfig.getSelectionModel().getSelectedItem() != null || tableViewConfigs.getSelectionModel().getSelectedItem() != null) {
            if (tableViewMyConfig.getSelectionModel().getSelectedItem() != null && tableViewConfigs.getSelectionModel().getSelectedItem() == null) {
                ApplicationData.getInstance().getRegisterProduct().setSelectedCar(tableViewMyConfig.getSelectionModel().getSelectedItem());
            } else {
                ApplicationData.getInstance().getRegisterProduct().setSelectedCar(tableViewConfigs.getSelectionModel().getSelectedItem());
            }
            try {
                Main.setRoot("usercarinfo");
            } catch (IOException e) {
                Dialogs.showErrorDialog("Oups", "Det har skjedd en feil i åpning av nytt vindu", e.getMessage());
            }
        } else {
            Dialogs.showErrorDialog("Oups", "Du må velge en bil først!", "Prøv igjen etter å ha valgt et produkt");
        }
    }

    @FXML
    private void openFileMenuClicked(ActionEvent event) {
        FileHandler.openFileCsv();
    }

    @FXML
    private void saveFileMenuClicked(ActionEvent event) {
        FileHandler.saveFileCsv();
    }
}
