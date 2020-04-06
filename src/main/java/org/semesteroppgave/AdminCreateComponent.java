package org.semesteroppgave;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.exceptions.*;
import org.semesteroppgave.gui.Dialogs;

import java.io.IOException;

public class AdminCreateComponent {

    public void addComponent(Label lblMessage, TableView<Component> tableViewAddedConfig, TextField version, TextField price, TextArea description){
        lblMessage.setText("");
        Component newComponent = null;
        try {
            //Tester om prisen er tom her fordi det er ikke mulig å teste på double inne i klassen
            if (price.getText().isEmpty()){
                throw new NullPointerException("Du har glemt å fylle inn prisen");
            }
            switch (Context.getInstance().getRegisterComponent().getNewComponent()){
                case "Motor": newComponent = new Motor(version.getText(), Double.parseDouble(price.getText()), description.getText());
                    break;
                case "Felg": newComponent = new Rim(version.getText(), Double.parseDouble(price.getText()), description.getText());
                    break;
                case "Setetrekk": newComponent = new SeatCover(version.getText(), Double.parseDouble(price.getText()), description.getText());
                    break;
                case "Spoiler": newComponent = new Spoiler(version.getText(), Double.parseDouble(price.getText()), description.getText());
                    break;
                case  "Dekk": newComponent = new Tires(version.getText(), Double.parseDouble(price.getText()), description.getText());
                    break;
                case "Batteri": newComponent = new Battery(version.getText(), Double.parseDouble(price.getText()), description.getText());
                    break;
                case "Tank": newComponent = new FuelContainer(version.getText(), Double.parseDouble(price.getText()), description.getText());
                    break;
                case "Girboks": newComponent = new Gearbox(version.getText(), Double.parseDouble(price.getText()), description.getText());
                    break;
                default: Dialogs.showErrorDialog("Legg til komponent", "Fant ikke komponenten", "Prøv igjen");
            }

            duplicateComponent(newComponent);

            if (newComponent != null){
                Context.getInstance().getRegisterComponent().setCreateComponentList(newComponent);
                tableViewAddedConfig.setItems(Context.getInstance().getRegisterComponent().getCreateComponentList());
                lblMessage.setText("Komponenten er lagt til");
                version.clear();
                price.clear();
                description.clear();
            }else {
                Dialogs.showErrorDialog("Oups!", "Feil i oppretting av komponent","Denne komponenten finnes fra før");
            }
        }catch (NullPointerException | NumberFormatException | DuplicateException | InvalidVersionException | InvalidDescriptionException e){
            Dialogs.showErrorDialog("Oups!", "Feil i oppretting av komponent", e.getMessage());
        }
    }

    private void duplicateComponent(Component component){

        for (Component createComponent : Context.getInstance().getRegisterComponent().getCreateComponentList()){
            if (createComponent.equals(component)){
                throw new DuplicateException("Komponenten finnes allerede");
            }
        }

        for (Component searchComponent : Context.getInstance().getRegisterComponent().getSearchResult()){
            if (searchComponent.equals(component)){
                throw new DuplicateException("Komponenten finnes allerede");
            }
        }

        for (Component listComponent : Context.getInstance().getRegisterComponent().getComponentsList()){
            if (listComponent.equals(component)){
                throw new DuplicateException("Komponenten finnes allerede");
            }
        }
    }

    public void completeComponent(){
        try {
            for (Component component : Context.getInstance().getRegisterComponent().getCreateComponentList()){
                Context.getInstance().getRegisterComponent().setComponentsList(component);
            }
            Context.getInstance().getRegisterComponent().getCreateComponentList().clear();
            Main.setRoot("admincomponent");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Dette er for å teste
        Motor motor = new Motor("V12",12300,"Denne V12 motoren er slitesterk og har en veldig høy ytelse");
        Context.getInstance().getRegisterComponent().setComponentsList(motor);
        Motor motor2 = new Motor("V8",1230, "Denne V8 motoren er lett å vedlikeholde og lager lite lyd");
        Context.getInstance().getRegisterComponent().setComponentsList(motor2);
        Rim rim = new Rim("Feit",3900,"Disse felgene er laget av aluminium som gir en fin glans i solen");
        Context.getInstance().getRegisterComponent().setComponentsList(rim);
        SeatCover seatCover = new SeatCover("Mykt",123,"Dette setetrekket er veldig komfortabelt under lange kjøreturer");
        Context.getInstance().getRegisterComponent().setComponentsList(seatCover);
        SeatCover seatCover2 = new SeatCover("Sport",12300,"Dette setetrekket er for deg som er ute etter fart. Mindre behagelig over tid");
        Context.getInstance().getRegisterComponent().setComponentsList(seatCover2);
        Spoiler spoiler = new Spoiler("Høy",1000,"Denne spoileren gir ekstra fart ettersom den er meget høy");
        Context.getInstance().getRegisterComponent().setComponentsList(spoiler);
        Spoiler spoiler2 = new Spoiler("Lav",500,"Denne spoileren gir mindre fart men god luftmotstand");
        Context.getInstance().getRegisterComponent().setComponentsList(spoiler2);
        Tires tires = new Tires("Vinter",1000,"Disse vinterdekkene er gode på alle typer snø og is");
        Context.getInstance().getRegisterComponent().setComponentsList(tires);
        Tires tires2 = new Tires("Sommer",1500,"Disse sommerdekkene er veldig slitesterke");
        Context.getInstance().getRegisterComponent().setComponentsList(tires2);
        Tires tires3 = new Tires("Alround",2000,"Disse dekkene kan du bruke både sommer og vinter");
        Context.getInstance().getRegisterComponent().setComponentsList(tires3);
        Gearbox gearbox = new Gearbox("Manuell",3000,"Manuell girboks er billigere enn automat, og gir deg mer kontroll");
        Context.getInstance().getRegisterComponent().setComponentsList(gearbox);
        Gearbox gearbox2 = new Gearbox("Automat",5000,"Automat girboks er dyrere enn manuell, men er mer komportabelt");
        Context.getInstance().getRegisterComponent().setComponentsList(gearbox2);
        Battery battery = new Battery("Li-ion",10000,"Li-ion batteri er miljøvennlige og har lang rekkevidde");
        Context.getInstance().getRegisterComponent().setComponentsList(battery);
        FuelContainer fuelContainer = new FuelContainer("50-liter",5000,"Denne tanken er ikke så stor, men er billig");
        Context.getInstance().getRegisterComponent().setComponentsList(fuelContainer);
        FuelContainer fuelContainer2 = new FuelContainer("100-liter",10000,"Denne tanken er stor og har god kondens");
        Context.getInstance().getRegisterComponent().setComponentsList(fuelContainer2);
        FuelContainer fuelContainer3 = new FuelContainer("200-liter",2000,"Denne tanken er giga stor og har god kondens");
        Context.getInstance().getRegisterComponent().setComponentsList(fuelContainer3);
    }

    private void convert(Component component){
        Component newComponent;
        switch (component.getComponent()){
            case "Motor": newComponent = new Motor(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Felg": newComponent = new Rim(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Setetrekk": newComponent = new SeatCover(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Spoiler": newComponent = new Spoiler(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Dekk": newComponent = new Tires(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Batteri": newComponent = new Battery(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Tank": newComponent = new FuelContainer(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            case "Girboks": newComponent = new Gearbox(component.getVersion(), component.getPrice(), component.getDescription());
                break;
            default: throw new InvalidComponentException("Fant ikke komponenten");
        }
        int index = Context.getInstance().getRegisterComponent().getComponentsList().indexOf(component);
        Context.getInstance().getRegisterComponent().getComponentsList().remove(component);
        //Her plasserer jeg det nye objektet på den tidligere plassen til det gamle objektet
        Context.getInstance().getRegisterComponent().getComponentsList().set(index, newComponent);
    }

    public void editPriceColumn(TableColumn.CellEditEvent<Component, Double> event, InputValidation.DoubleStringConverter doubleStrConverter, TableView<Component> tableViewAddedConfig) {
        try {
            if(doubleStrConverter.wasSuccessful()){
                event.getRowValue().setPrice(event.getNewValue());
            }
        } catch (NumberFormatException e) {
            Dialogs.showErrorDialog("Feil,","Feil i pris", "Du må skrive inn et positivt tall");
        } catch (IllegalArgumentException e) {
            Dialogs.showErrorDialog("Feil","Ugyldig pris: ", e.getMessage());
        }
        tableViewAddedConfig.refresh();
    }

    public void editComponentColumn(TableColumn.CellEditEvent<Component, String> event, TableView<Component> tableViewComponents){
        try{
            event.getRowValue().setComponent(InputValidation.testValidComponent(event.getNewValue()));
            convert(event.getTableView().getSelectionModel().getSelectedItem());
        }catch (InvalidComponentException e){
            Dialogs.showErrorDialog("Redigeringsfeil","Ugyldig komponent!", e.getMessage());
            tableViewComponents.refresh();
        }
    }

    public void editVersionColumn(TableColumn.CellEditEvent<Component, String> event, TableView<Component> tableViewComponents){
        try{
            event.getRowValue().setVersion(InputValidation.testValidVersion(event.getNewValue()));

        }catch (InvalidVersionException e){
            Dialogs.showErrorDialog("Redigeringsfeil","Ugyldig versjon!", e.getMessage());
            tableViewComponents.refresh();
        }
    }

    public void deleteColumn(TableView<Component> tableViewComponents, ObservableList<Component> list, boolean state){
        try {
            if (tableViewComponents.getSelectionModel().getSelectedItem() != null){
                if (state){
                    //Hvis state er true er det adminComponentController som endres på
                    //Her finner jeg ut om det er mulig å slette en komponent
                    //Det må være minst én av hver komponent
                    int counter = 0;
                    for (Component component : Context.getInstance().getRegisterComponent().getComponentsList()){
                        if (component.getComponent().equals(tableViewComponents.getSelectionModel().getSelectedItem().getComponent())){
                            counter++;
                        }
                    }
                    //Hvis denne intreffer er det bare én av denne type komponent igjen og vi kaster et avvik
                    if (counter == 1){
                        throw new InvalidDeleteException("Kan ikke slette flere av denne komponenten " +
                                "fordi det må være minst én av denne komponenten. Hvis det manger en komponent nå brukeren" +
                                " oppretter en bil vil brukeren ikke ha mulighet til å opprette en bil");
                    }
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Bekreft");
                alert.setHeaderText("Du har valgt komponenten: " + tableViewComponents.getSelectionModel().getSelectedItem().getComponent()
                        + ", versjon: "+ tableViewComponents.getSelectionModel().getSelectedItem().getVersion());
                alert.setContentText("Ønsker du virkerlig å slette denne komponenten?");
                alert.showAndWait().ifPresent(response -> {
                    if(response == ButtonType.OK){
                        list.remove(tableViewComponents.getSelectionModel().getSelectedItem());
                        //Tester om vi finner det samme objektet i søkerListen. Hvis så, sletter vi det også
                        for (Component component : Context.getInstance().getRegisterComponent().getSearchResult()){
                            if (component.equals(tableViewComponents.getSelectionModel().getSelectedItem())){
                                Context.getInstance().getRegisterComponent().getSearchResult().remove(component);
                                break;
                            }
                        }
                    }
                });
            }else {
                Dialogs.showErrorDialog("Feil", "Du har ikke valgt en komponent", "Velg en komponent og prøv på nytt");
            }
        } catch (InvalidDeleteException e) {
            Dialogs.showErrorDialog("Ugyldig slett", "Du kan ikke slette denne komponenten", e.getMessage());
        }
    }
}
