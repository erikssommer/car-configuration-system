package org.semesteroppgave;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.semesteroppgave.models.filehandlers.FileHandler;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.File;
import java.io.IOException;

/**
 * JavaFX Main
 */
public class Main extends Application {

    private static Scene scene;
    public static boolean folderCreated;

    public static Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage stage) throws IOException {
        //Lager en mappe lokalt på datamaskinen for lagring av filer
        createFolderOnComputer();
        //Lagring av data ved avslutting av programmet
        onProgramExit(stage);
        //Laster inn ferdiglagde podukter
        loadConfiguredProducts();
        scene = new Scene(loadFXML("usersignin"));
        scene.getStylesheets().add(getClass().getResource("views/stylesheet.css").toExternalForm());
        stage.setTitle("DataMet Bilkonfigurering");
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
        FileHandler.openFileCvsLaunch(getClass().getResource("/org/semesteroppgave/files/onApplicationLaunch/produkter.csv").getFile());
    }

    private void createFolderOnComputer(){
        File filePath = new File(System.getProperty("user.home") + File.separator + "SemesteroppgaveLagredeFiler");
        if (!filePath.exists()){
            if (filePath.mkdirs()){
                System.out.println("Oppretter mappe lokalt på datamaskinen");
                folderCreated = true;
            } else {
                System.out.println("Klarte ikke å opprette mappe");
                folderCreated = false;
            }
        }else {
            System.out.println("Mappen eksisterer fra før");
            folderCreated = true;
        }
    }

    //Lagrer til fil når programmet avsluttes hvis listene ikke er tomme og mappen er opprettet
    private void onProgramExit(Stage stage) {
        stage.setOnCloseRequest(windowEvent -> {
            if (!ApplicationData.getInstance().getRegisterProduct().getUserProductList().isEmpty() ||
                    !ApplicationData.getInstance().getRegisterComponent().getComponentList().isEmpty()) {
                Dialogs.showConfirmationDialog("Ønsker du å lagre endringer før programmet avsluttes?","",
                        response -> {
                            if (response == ButtonType.OK) {
                                if (folderCreated){
                                    if (!ApplicationData.getInstance().getRegisterProduct().getUserProductList().isEmpty()) {
                                        FileHandler.saveFileCsvOnProgramExit(System.getProperty("user.home") + "/"
                                                + "SemesteroppgaveLagredeFiler/lagredeProdukter.csv");
                                    }
                                    if (!ApplicationData.getInstance().getRegisterComponent().getComponentList().isEmpty()) {
                                        FileHandler.saveFileJobjOnProgramExit(System.getProperty("user.home") + "/"
                                                + "SemesteroppgaveLagredeFiler/lagredeKomponenter.jobj");
                                    }
                                }else {
                                    System.out.println("Kunne ikke lagre fordi mappen ikke har blitt oprettet");
                                }
                            }
                        });
            }

        });
    }
}