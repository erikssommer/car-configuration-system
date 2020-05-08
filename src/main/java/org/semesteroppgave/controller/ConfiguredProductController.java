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
            Dialogs.showErrorDialog("Oups", "Du må markere produktet ditt først!",
                    "Prøv igjen etter å ha valgt din konfigurasjon");
        }
    }

    @FXML
    private void btnMoreInfo() {
        getSelectedTable(tvConfigurations, tvUserConfigurations);
    }

    //Metode som finner tabellen og rad som er markert og setter den som makert produkt
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
                        "Det har skjedd en feil i åpning av nytt vindu", e.getMessage());
            }
        } else {
            Dialogs.showErrorDialog("Oups", "Du må velge en bil først!",
                    "Prøv igjen etter å ha valgt et produkt");
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
            Dialogs.showErrorDialog("Fil", "Feil i lagring av liste",
                    "Du kan ikke lagre en tom liste");
        }
    }

    @FXML
    private void deleteProduct() {
        if (tvUserConfigurations.getSelectionModel().getSelectedItem() != null) {
            Dialogs.showConfirmationDialog("Du har valgt produktmodellen: " +
                            tvUserConfigurations.getSelectionModel().getSelectedItem().getModel(),
                    "Ønsker du virkelig å slette dette produktet?",
                    response -> {
                        if (response == ButtonType.OK) {
                            ApplicationData.getInstance().getRegisterProduct().getUserProductList()
                                    .remove(tvUserConfigurations.getSelectionModel().getSelectedItem());
                        }
                    });
        } else {
            Dialogs.showErrorDialog("Feil", "Du har ikke valgt et produkt",
                    "Velg et produkt og prøv på nytt");
        }
    }
}
