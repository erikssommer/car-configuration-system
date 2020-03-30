package org.semesteroppgave;

import javafx.scene.control.TableView;
import org.semesteroppgave.carcomponents.Component;

public class UserCreateCar {

    private TableView<Component> tableViewComponent;
    private TableView<Component> tableViewVersion;

    public UserCreateCar(TableView<Component> tableViewComponent, TableView<Component> tableViewVersion){
        this.tableViewComponent = tableViewComponent;
        this.tableViewVersion = tableViewVersion;
    }

    public void createNewCar(String model1, String model2){
        Context.getInstance().getRegisterComponent().clearModelComponentsList();
        for (Component model : Context.getInstance().getRegisterComponent().getComponentsList()){
            for (String componentModel : model.getModel()){
                if (componentModel.equals(model1) || componentModel.equals(model2)){
                    if (!redundance(model.getComponent())){
                        Context.getInstance().getRegisterComponent().setModelComponentsList(model);
                    }
                }
            }
        }
        tableViewComponent.setItems(Context.getInstance().getRegisterComponent().getModelComponentsList());

        tableViewComponent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            showComponents(tableViewComponent.getSelectionModel().getSelectedItem().getComponent());
        });
    }

    public boolean redundance(String componentModel){
        boolean redundance = false;
        for (Component component : Context.getInstance().getRegisterComponent().getModelComponentsList()){
            redundance = componentModel.equals(component.getComponent());
        }
        return redundance;
    }

    public void showComponents(String selectedComponent){
        Context.getInstance().getRegisterComponent().clearChooseComponentList();
        for (Component component : Context.getInstance().getRegisterComponent().getComponentsList()){
            if (component.getComponent().equals(selectedComponent)){
                Context.getInstance().getRegisterComponent().setChooseComponentList(component);
            }
        }
        tableViewVersion.setItems(Context.getInstance().getRegisterComponent().getChooseComponentList());
        tableViewVersion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            addToCar();
        });
    }

    public void addToCar(){

    }
}
