package org.semesteroppgave.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.semesteroppgave.Context;
import org.semesteroppgave.Main;
import org.semesteroppgave.carcomponents.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminCreateController implements Initializable {

    private Component newComponent = null;

    @FXML
    private Label lblComponent;

    @FXML
    private TextField txtVersion, txtPrice;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TableView<Component> tableViewAddedConfig;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblComponent.setText(Context.getInstance().getRegisterComponent().getNewComponent().toLowerCase());
    }

    @FXML
    void btnAdd(ActionEvent event) {
        switch (Context.getInstance().getRegisterComponent().getNewComponent()){
            case "Motor": newComponent = new Motor(txtVersion.getText(), Double.parseDouble(txtPrice.getText()));
                break;
            case "Felg": newComponent = new Rim(txtVersion.getText(), Double.parseDouble(txtPrice.getText()));
                break;
            case "Setetrekk": newComponent = new SeatCover(txtVersion.getText(), Double.parseDouble(txtPrice.getText()));
                break;
            case "Ratt": newComponent = new SteeringWheel(txtVersion.getText(), Double.parseDouble(txtPrice.getText()));
                break;
            case "Spoiler": newComponent = new Spoiler(txtVersion.getText(), Double.parseDouble(txtPrice.getText()));
                break;
            case  "Dekk": newComponent = new Tires(txtVersion.getText(), Double.parseDouble(txtPrice.getText()));
                break;
            case "Batteri": newComponent = new Battery(txtVersion.getText(), Double.parseDouble(txtPrice.getText()));
                break;
            case "Tank": newComponent = new FuelContainer(txtVersion.getText(), Double.parseDouble(txtPrice.getText()));
                break;
            case "Girboks": newComponent = new Gearbox(txtVersion.getText(), Double.parseDouble(txtPrice.getText()));
                break;
            default: Dialogs.showErrorDialog("Legg til komponent", "Fant ikke komponenten", "Prøv igjen");
        }
        Context.getInstance().getRegisterComponent().setComponentsList(newComponent);
    }

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Main.setRoot("admincomponent");
    }

    @FXML
    void btnComplete(ActionEvent event) {

    }
}
