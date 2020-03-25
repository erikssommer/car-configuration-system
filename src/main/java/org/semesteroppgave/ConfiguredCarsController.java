package org.semesteroppgave;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

public class ConfiguredCarsController {
    @FXML
    private TableView<?> tableViewConfigs;

    @FXML
    private TableView<?> tableViewMyConfig;

    @FXML
    void btnBack(ActionEvent event) throws IOException {
        Main.setRoot("userbuildcar");
    }

    @FXML
    void btnExport(ActionEvent event) {

    }

    @FXML
    void btnMoreInfo(ActionEvent event) throws IOException {
        Main.setRoot("usercarinfo");
    }
}
