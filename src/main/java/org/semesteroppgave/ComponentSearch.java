package org.semesteroppgave;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import org.semesteroppgave.carcomponents.Component;
import org.semesteroppgave.exceptions.InvalidPriceException;
import org.semesteroppgave.gui.Dialogs;

public class ComponentSearch {
    ObservableList<String> componentFilter = FXCollections.observableArrayList();
    ObservableList<Component> searchResult = FXCollections.observableArrayList();

    public void search(String choiceFilter, String searchWord, TableView<Component> tableView){
        searchResult.clear();

        switch (choiceFilter){
            case "Komponent" : searchResult.addAll(Context.getInstance().getRegisterComponent().searchComponent(searchWord));
                break;
            case "Versjon" : searchResult.addAll(Context.getInstance().getRegisterComponent().searchVersion(searchWord));
                break;
            case "Pris" : try {
                searchResult.addAll(Context.getInstance().getRegisterComponent().searchPrice(Double.parseDouble(searchWord)));
            }catch (NumberFormatException ignored){}
                break;
        }

        tableView.setItems(searchResult);

    }

    public void loadFilter(ComboBox<String> cbFilter) {
        componentFilter.removeAll();
        String choice1 = "Komponent";
        String choise2 = "Versjon";
        String choise3 = "Pris";
        componentFilter.addAll(choice1, choise2, choise3);
        cbFilter.getItems().addAll(componentFilter);
        cbFilter.setValue(choice1);
    }

}
