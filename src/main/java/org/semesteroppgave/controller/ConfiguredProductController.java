package org.semesteroppgave.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.data.productmodels.Product;
import org.semesteroppgave.models.filehandlers.FileHandler;
import org.semesteroppgave.models.signin.user.UserSignIn;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.IOException;

public class ConfiguredProductController implements ApplicationController {

    @FXML
    private Label lblUsername;

    @FXML
    private TableView<Product> tvConfigurations;

    @FXML
    private TableView<Product> tvUserConfigurations;

    @Override
    public void initialize() {

        tvConfigurations.setItems(ApplicationData.getInstance().getRegisterProduct().getProductList());
        tvUserConfigurations.setItems(ApplicationData.getInstance().getRegisterProduct().getUserProductList());

        lblUsername.setText(UserSignIn.getActiveUsername());
    }

    @FXML
    private void btnBack() throws IOException {
        Main.setRoot("userbuildproduct");
    }

    @FXML
    private void btnAddToConfigs() {

        if (tvConfigurations.getSelectionModel().getSelectedItem() != null) {
            ApplicationData.getInstance().getRegisterProduct()
                    .setUserProductList(tvConfigurations.getSelectionModel().getSelectedItem());
            ApplicationData.getInstance().getRegisterProduct()
                    .getProductList().remove(tvConfigurations.getSelectionModel().getSelectedItem());

        } else {
            Dialogs.showErrorDialog("Oups", "You must select your product first!",
                    "Please try again after selecting your configuration");
        }
    }

    @FXML
    private void btnMoreInfo() {
        getSelectedTable(tvConfigurations, tvUserConfigurations);
    }

    //Method that finds the table and row that is highlighted and sets it as the selected product
    private void getSelectedTable(TableView<Product> tableViewUserConfig, TableView<Product> tableViewConfigs) {
        var userConfig = tableViewUserConfig.getSelectionModel().getSelectedItem();
        var previousConfig = tableViewConfigs.getSelectionModel().getSelectedItem();

        if (userConfig != null || previousConfig != null) {
            if (userConfig != null && previousConfig == null) {
                ApplicationData.getInstance().getRegisterProduct().setSelectedProduct(userConfig);
            } else {
                ApplicationData.getInstance().getRegisterProduct().setSelectedProduct(previousConfig);
            }
            try {
                Main.setRoot("userproductinfo");
            } catch (IOException e) {
                Dialogs.showErrorDialog("Oups",
                        "There was an error opening a new window", e.getMessage());
            }
        } else {
            Dialogs.showErrorDialog("Oups", "You must choose a product first!",
                    "Please try again after selecting a product");
        }
    }

    @FXML
    private void openFileMenuClicked() {
        FileHandler.openFileCsv();
    }

    @FXML
    private void saveFileMenuClicked() {
        if (!tvUserConfigurations.getItems().isEmpty()) {
            FileHandler.saveFileCsv();
        } else {
            Dialogs.showErrorDialog("File", "Error saving list",
                    "You cannot save an empty list");
        }
    }

    @FXML
    private void deleteProduct() {
        if (tvUserConfigurations.getSelectionModel().getSelectedItem() != null) {
            Dialogs.showConfirmationDialog("You have selected the product model: " +
                            tvUserConfigurations.getSelectionModel().getSelectedItem().getModel(),
                    "Do you really want to delete this product?",
                    response -> {
                        if (response == ButtonType.OK) {
                            ApplicationData.getInstance().getRegisterProduct().getUserProductList()
                                    .remove(tvUserConfigurations.getSelectionModel().getSelectedItem());
                        }
                    });
        } else {
            if (tvConfigurations.getSelectionModel().getSelectedItem() != null) {
                Dialogs.showErrorDialog("Error", "You have selected the wrong list",
                        "It is only possible to delete your own configurations");
            } else {
                Dialogs.showErrorDialog("Error", "You have not selected a product",
                        "Please select a product and try again");
            }
        }
    }
}
