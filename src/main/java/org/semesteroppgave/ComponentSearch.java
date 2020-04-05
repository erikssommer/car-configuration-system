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

    ObservableList<String> componentFilter = FXCollections.observableArrayList();

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
        Context.getInstance().getRegisterComponent().getSearchResult().clear();

        switch (choiceFilter){
            case "Komponent" : Context.getInstance().getRegisterComponent().getSearchResult().addAll(Context.getInstance().getRegisterComponent().searchComponent(searchWord));
                break;
            case "Versjon" : Context.getInstance().getRegisterComponent().getSearchResult().addAll(Context.getInstance().getRegisterComponent().searchVersion(searchWord));
                break;
            case "Pris" : try {
                Context.getInstance().getRegisterComponent().getSearchResult().addAll(Context.getInstance().getRegisterComponent().searchPrice(Double.parseDouble(searchWord)));
            }catch (NumberFormatException ignored){}
                break;
        }
        tableView.setItems(Context.getInstance().getRegisterComponent().getSearchResult());
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
