package org.semesteroppgave.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import org.semesteroppgave.ApplicationData;
import org.semesteroppgave.Main;
import org.semesteroppgave.models.services.AdminCreateComponent;
import org.semesteroppgave.models.data.components.Component;
import org.semesteroppgave.models.exceptions.*;
import org.semesteroppgave.models.filehandlers.FileHandler;
import org.semesteroppgave.models.signin.admin.AdminSignIn;
import org.semesteroppgave.models.utilities.alerts.Dialogs;
import org.semesteroppgave.models.utilities.converters.DoubleConverter;
import org.semesteroppgave.models.utilities.search.ComponentSearch;
import org.semesteroppgave.models.utilities.threadhelper.StartThread;

import java.io.IOException;

public class AdminComponentController implements ApplicationThread {

    private final DoubleConverter.DoubleStringConverter doubleStrConverter = new DoubleConverter.DoubleStringConverter();
    private final ComponentSearch componentSearch = new ComponentSearch();
    private final AdminCreateComponent createComponent = new AdminCreateComponent(componentSearch);
    private StartThread startThread;
    public MenuBar menuBar = new MenuBar();

    @FXML
    private Tab tabCreate;

    @FXML
    private Button btnSignOut, btnDeleteComponent, btnDescription;

    @FXML
    private TableView<Component> tableViewComponents, tableViewCreate;

    @FXML
    private TableColumn<Component, Double> txtPriceColumn, txtPriceColumnCreate;

    @FXML
    private ComboBox<String> cbFilter, cbCreate;

    @FXML
    private TextField txtSearch, txtVersion, txtPrice;

    @FXML
    private TextArea txtDescription, txtEditDescription;

    @FXML
    private Label lblAdminID, lblMessageCreate, lblThreadMessage;

    @FXML
    private ProgressBar progressBar;


    @Override
    public void initialize() {
        startThread = new StartThread(this, lblThreadMessage, progressBar);
        tableViewComponents.setItems(ApplicationData.getInstance().getRegisterComponent().getComponentList());
        txtPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(doubleStrConverter));
        txtPriceColumnCreate.setCellFactory(TextFieldTableCell.forTableColumn(doubleStrConverter));
        componentSearch.loadFilter(cbFilter);
        createComponent.loadChoice(cbCreate);
        lblAdminID.setText(AdminSignIn.getActiveAdminId());
        progressBar.setVisible(false);
        lblThreadMessage.setVisible(false);
        btnDescription.setVisible(false);
        txtEditDescription.setDisable(true);
    }

    @FXML
    private void btnAdd() {
        try {
            lblMessageCreate.setText("");
            createComponent.addComponent(tableViewCreate, txtVersion, txtPrice, txtDescription, cbCreate);
            lblMessageCreate.setText("Komponenten er lagt til");
        } catch (EmptyComponentException | DuplicateException | InvalidVersionException | InvalidDescriptionException | InvalidPriceException e) {
            Dialogs.showErrorDialog("Oups!", "Feil i oppretting av komponent", e.getMessage());
        } catch (NumberFormatException nfe) {
            Dialogs.showErrorDialog("Oups!", "Feil i oppretting av komponent",
                    "Prisen må være fyllt inn og være et tall");
        }
    }

    @FXML
    private void btnComplete() {
        try {
            createComponent.completeComponent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnDeleteComponent() {
        try {
            createComponent.deleteRow(tableViewComponents, ApplicationData.getInstance().getRegisterComponent().getComponentList(), true);
            txtEditDescription.setText("");
            txtEditDescription.setDisable(true);
        } catch (InvalidDeleteException e) {
            Dialogs.showErrorDialog("Ugyldig slett", "Du kan ikke slette denne komponenten", e.getMessage());
        }
    }

    @FXML
    private void btnDeleteFromRegisterNewComponent() {
        try {
            createComponent.deleteRow(tableViewCreate, createComponent.getCreateComponentList(), false);
        } catch (InvalidDeleteException e) {
            Dialogs.showErrorDialog("Ugyldig slett", "Du kan ikke slette denne komponenten", e.getMessage());
        }
    }

    @FXML
    private void btnSignout() throws IOException {
        Main.setRoot("adminsignin");
    }

    //Has chosen to treat / catch exceptions in the model and not in the controller when editing in tableview
    //This is because two and two edit methods share the same method and will then avoid duplication of code
    @FXML
    private void editComponent(TableColumn.CellEditEvent<Component, String> event) {
        createComponent.editComponentColumn(event, tableViewComponents);
    }

    @FXML
    private void editPrice(TableColumn.CellEditEvent<Component, Double> event) {
        createComponent.editPriceColumn(event, doubleStrConverter, tableViewComponents);
    }

    @FXML
    private void editPriceCreate(TableColumn.CellEditEvent<Component, Double> event) {
        createComponent.editPriceColumn(event, doubleStrConverter, tableViewCreate);
    }

    @FXML
    private void editVersion(TableColumn.CellEditEvent<Component, String> event) {
        createComponent.editVersionColumn(event, tableViewComponents);
    }

    @FXML
    private void editVersionCreate(TableColumn.CellEditEvent<Component, String> event) {
        createComponent.editVersionColumn(event, tableViewCreate);
    }

    @FXML
    private void filterChoiceChanged() {
        componentSearch.filter(txtSearch, tableViewComponents, cbFilter);
    }

    @FXML
    private void onKeyTypedSearch() {
        componentSearch.filter(txtSearch, tableViewComponents, cbFilter);
        txtEditDescription.setDisable(true);
        txtEditDescription.setText("");
        btnDescription.setVisible(false);
    }

    @FXML
    private void tabComponent() {
        menuBar.setVisible(true);
    }

    @FXML
    private void tabCreate() {
        menuBar.setVisible(false);
    }

    @FXML
    private void openFileMenuClicked() {
        String filePath = FileHandler.getOpenFileJobj();
        if (!filePath.equals("null")) {
            progressBar.setVisible(true);
            lblThreadMessage.setVisible(true);
            btnDescription.setVisible(false);
            txtEditDescription.setText("");
            startThread.start(filePath);
        }
    }

    @FXML
    private void saveFileMenuClicked() {
        if (!tableViewComponents.getItems().isEmpty()) {
            FileHandler.saveFileJobj(componentSearch);
        } else {
            Dialogs.showErrorDialog("Fil", "Feil i lagring av liste",
                    "Du kan ikke lagre en tom liste");
        }
    }

    @FXML
    private void showDescription() {
        if (tableViewComponents.getSelectionModel().getSelectedItem() != null) {
            txtEditDescription.setText(tableViewComponents.getSelectionModel().getSelectedItem().getDescription());
            btnDescription.setVisible(false);
            txtEditDescription.setDisable(false);
        }
    }

    @FXML
    private void editDescription() {
        btnDescription.setVisible(true);
    }

    @FXML
    void btnDescription() {
        try {
            tableViewComponents.getSelectionModel().getSelectedItem().setDescription(txtEditDescription.getText());
        } catch (InvalidDescriptionException e) {
            Dialogs.showErrorDialog("Redigeringsfeil", "Ugyldig beskrivelse!", e.getMessage());
            txtEditDescription.setText(tableViewComponents.getSelectionModel().getSelectedItem().getDescription());
        }
        btnDescription.setVisible(false);
    }

    @Override
    public void disableGUI() {
        tabCreate.setDisable(true);
        txtSearch.setDisable(true);
        cbFilter.setDisable(true);
        btnDeleteComponent.setDisable(true);
        btnSignOut.setDisable(true);
        menuBar.setDisable(true);
        tableViewComponents.setDisable(true);
        btnDescription.setDisable(true);
        txtEditDescription.setDisable(true);
    }

    @Override
    public void enableGUI() {
        tabCreate.setDisable(false);
        txtSearch.setDisable(false);
        cbFilter.setDisable(false);
        btnDeleteComponent.setDisable(false);
        btnSignOut.setDisable(false);
        menuBar.setDisable(false);
        tableViewComponents.setDisable(false);
        progressBar.setVisible(false);
        lblThreadMessage.setVisible(false);
        btnDescription.setDisable(false);
        txtEditDescription.setDisable(true);
    }
}
