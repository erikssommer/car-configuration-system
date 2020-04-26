package org.semesteroppgave.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.data.productmodels.Product;
import org.semesteroppgave.models.filehandlers.FileHandler;
import org.semesteroppgave.models.signin.user.UserSignIn;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.IOException;

public class ConfiguredProductController {
    @FXML
    private Label lblUsername;
    @FXML
    private TableView<Product> tableViewConfigs;

    @FXML
    private TableView<Product> tableViewMyConfig;

    @FXML
    private TableColumn<Product, Double> txtPriceColumn;

    @FXML
    private TableColumn<Product, Double> txtPriceColumnMy;

    @FXML
    private TableColumn<Product, String> txtMotorColumn, txtRimColumn, txtSeatcoverColumn, txtSpoilerColumn, txtTireColumn;

    @FXML
    private TableColumn<Product, String> txtMotorColumnMy, txtRimColumnMy, txtSeatcoverColumnMy, txtSpoilerColumnMy, txtTireColumnMy;

    public void initialize() {
        setTableColum(txtMotorColumn, txtRimColumn, txtSeatcoverColumn, txtSpoilerColumn, txtTireColumn, txtPriceColumn);
        tableViewConfigs.setItems(ApplicationData.getInstance().getRegisterProduct().getProductList());

        setTableColum(txtMotorColumnMy, txtRimColumnMy, txtSeatcoverColumnMy, txtSpoilerColumnMy, txtTireColumnMy, txtPriceColumnMy);
        tableViewMyConfig.setItems(ApplicationData.getInstance().getRegisterProduct().getUserProductList());

        lblUsername.setText(UserSignIn.getActiveUsername());
    }

    private void setTableColum(TableColumn<Product, String> txtMotorColumn, TableColumn<Product, String> txtRimColumn, TableColumn<Product, String> txtSeatcoverColumn, TableColumn<Product, String> txtSpoilerColumn, TableColumn<Product, String> txtTireColumn, TableColumn<Product, Double> txtPriceColumn) {
        txtMotorColumn.setCellValueFactory(new PropertyValueFactory<>("motor"));
        txtRimColumn.setCellValueFactory(new PropertyValueFactory<>("rim"));
        txtSeatcoverColumn.setCellValueFactory(new PropertyValueFactory<>("seatCover"));
        txtSpoilerColumn.setCellValueFactory(new PropertyValueFactory<>("spoiler"));
        txtTireColumn.setCellValueFactory(new PropertyValueFactory<>("tires"));
        txtPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    @FXML
    private void btnBack() throws IOException {
        Main.setRoot("userbuildproduct");
    }

    @FXML
    private void btnAddToConfigs() {

        if (tableViewMyConfig.getSelectionModel().getSelectedItem() != null) {
            ApplicationData.getInstance().getRegisterProduct().setProductList(tableViewMyConfig.getSelectionModel().getSelectedItem());
            ApplicationData.getInstance().getRegisterProduct().getUserProductList().remove(tableViewMyConfig.getSelectionModel().getSelectedItem());

        } else {
            Dialogs.showErrorDialog("Oups", "Du må markere produktet ditt først!", "Prøv igjen etter å ha valgt din konfigurasjon");
        }
    }

    @FXML
    private void btnMoreInfo() {
        chooseTable(tableViewConfigs, tableViewMyConfig);
    }

    private void chooseTable(TableView<Product> tableViewMyConfig, TableView<Product> tableViewConfigs) {
        if (tableViewMyConfig.getSelectionModel().getSelectedItem() != null || tableViewConfigs.getSelectionModel().getSelectedItem() != null) {
            if (tableViewMyConfig.getSelectionModel().getSelectedItem() != null && tableViewConfigs.getSelectionModel().getSelectedItem() == null) {
                ApplicationData.getInstance().getRegisterProduct().setSelectedProduct(tableViewMyConfig.getSelectionModel().getSelectedItem());
            } else {
                ApplicationData.getInstance().getRegisterProduct().setSelectedProduct(tableViewConfigs.getSelectionModel().getSelectedItem());
            }
            try {
                Main.setRoot("userproductinfo");
            } catch (IOException e) {
                Dialogs.showErrorDialog("Oups", "Det har skjedd en feil i åpning av nytt vindu", e.getMessage());
            }
        } else {
            Dialogs.showErrorDialog("Oups", "Du må velge en bil først!", "Prøv igjen etter å ha valgt et produkt");
        }
    }

    @FXML
    private void openFileMenuClicked() {
        FileHandler.openFileCsv();
    }

    @FXML
    private void saveFileMenuClicked() {
        if (!tableViewMyConfig.getItems().isEmpty()){
            FileHandler.saveFileCsv();
        }else {
            Dialogs.showErrorDialog("Fil", "Feil i lagring av liste", "Du kan ikke lagre en tom liste");
        }
    }
}
