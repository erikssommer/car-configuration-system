package org.semesteroppgave;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.semesteroppgave.carcomponents.Component;
import org.semesteroppgave.exceptions.InvalidPriceException;
import org.semesteroppgave.gui.Dialogs;

public class ComponentSearch {

    private ObservableList<Component> searchResult = FXCollections.observableArrayList();
    private ObservableList<String> componentFilter = FXCollections.observableArrayList();

    //Singelton design-pattern
    public static ComponentSearch componentSearch = null;

    public static ComponentSearch getInstance(){
        if (componentSearch == null){
            componentSearch = new ComponentSearch();
        }
        return componentSearch;
    }

    public void filter(TextField txtSearch, TableView<Component> tableViewComponents, ComboBox<String> cbFilter){
        if(txtSearch.getText().isEmpty()) {
            tableViewComponents.setItems(Context.getInstance().getRegisterComponent().getComponentsList());
        }else {
            String choiceFilter = cbFilter.getValue();
            String searchWord = txtSearch.getText();
            try {
                search(choiceFilter,searchWord,tableViewComponents);
            }catch (InvalidPriceException e){
                Dialogs.showErrorDialog("Feil i søket", e.getMessage(), "Prøv på nytt");
            }
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
        componentFilter.removeAll();
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
