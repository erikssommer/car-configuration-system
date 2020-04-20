package org.semesteroppgave.models.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.semesteroppgave.Context;
import org.semesteroppgave.models.data.carcomponents.Component;
import org.semesteroppgave.models.exceptions.InvalidPriceException;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

public class ComponentSearch {

    private ObservableList<Component> searchResult = FXCollections.observableArrayList();
    private ObservableList<String> componentFilter = FXCollections.observableArrayList();

    public void filter(TextField txtSearch, TableView<Component> tableViewComponents, ComboBox<String> cbFilter) throws IllegalArgumentException{
        if(txtSearch.getText().isEmpty()) {
            tableViewComponents.setItems(Context.getInstance().getRegisterComponent().getComponentsList());
        }else {
            String choiceFilter = cbFilter.getValue();
            String searchWord = txtSearch.getText();
            search(choiceFilter,searchWord,tableViewComponents);
        }
    }

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
        componentFilter.clear();
        String choice1 = "Komponent";
        String choice2 = "Versjon";
        String choice3 = "Pris";
        componentFilter.addAll(choice1, choice2, choice3);
        cbFilter.getItems().addAll(componentFilter);
        cbFilter.setValue(choice1);
    }

    public ObservableList<Component> getSearchResult(){
        return this.searchResult;
    }
}