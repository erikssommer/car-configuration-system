package org.semesteroppgave;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.semesteroppgave.models.filehandlers.FileHandler;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Main extends Application {

    private static Scene scene;

    public static Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage stage) throws IOException {
        //Lagring av data ved avslutting av programmet
        onProgramExit(stage);
        //Laster inn ferdiglagde podukter
        loadConfiguredProducts();
        scene = new Scene(loadFXML("usersignin"));
        stage.setTitle("Bilregistrering");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(750);
        stage.setHeight(550);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    private void loadConfiguredProducts() {
        FileHandler.openFileCvsLaunch();
    }

    private void onProgramExit(Stage stage) {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                if (!ApplicationData.getInstance().getRegisterProduct().getMyCarList().isEmpty() ||
                        !ApplicationData.getInstance().getRegisterComponent().getComponentsList().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Lukking av programmet");
                    alert.setHeaderText("Lagre før lukking");
                    alert.setContentText("Ønsker du å lagre endringer før programmet avsluttes?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            FileHandler.saveFileCsv();
                            FileHandler.saveFileJobj();
                        }
                    });
                }
            }
        });
    }
}