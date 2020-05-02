package org.semesteroppgave;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.semesteroppgave.models.filehandlers.FileHandler;

import java.io.IOException;

/**
 * JavaFX Main
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
        scene.getStylesheets().add(getClass().getResource("views/stylesheet.css").toExternalForm());
        stage.setTitle("dataMet Bilkonfigurering");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(750);
        stage.setHeight(575);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    private void loadConfiguredProducts() {
        FileHandler.openFileCvsLaunch();
    }

    //Lagrer til fil når programmet avsluttes hvis listene ikke er tomme
    private void onProgramExit(Stage stage) {
        stage.setOnCloseRequest(windowEvent -> {
            if (!ApplicationData.getInstance().getRegisterProduct().getUserProductList().isEmpty() ||
                    !ApplicationData.getInstance().getRegisterComponent().getComponentList().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Lukking av programmet");
                alert.setHeaderText("Lagre før lukking");
                alert.setContentText("Ønsker du å lagre endringer før programmet avsluttes?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        if (!ApplicationData.getInstance().getRegisterProduct().getUserProductList().isEmpty()){
                            FileHandler.saveFileCsvOnProgramExit();
                        }
                        if (!ApplicationData.getInstance().getRegisterComponent().getComponentList().isEmpty()){
                            FileHandler.saveFileJobjOnProgramExit();
                        }
                    }
                });
            }
        });
    }
}