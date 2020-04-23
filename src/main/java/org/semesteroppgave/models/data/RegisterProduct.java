package org.semesteroppgave.models.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.semesteroppgave.models.data.carmodel.Product;

public class RegisterProduct {
    //Listen carList er alle tidligere konfigurerte biler
    //Listen myCarList er min konfigurerte bil
    //Objektet selectedCar er den bilen som skal vises mer informasjon om
    private final ObservableList<Product> productList = FXCollections.observableArrayList();
    private final ObservableList<Product> myProductList = FXCollections.observableArrayList();
    private Product selectedProduct;

    public ObservableList<Product> getProductList() {
        return this.productList;
    }

    public void setProductList(Product product) {
        productList.add(product);
    }

    public ObservableList<Product> getMyProductList() {
        return this.myProductList;
    }

    public void setMyProductList(Product product) {
        myProductList.add(product);
    }

    public Product getSelectedProduct() {
        return this.selectedProduct;
    }

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product product : myProductList) {
            sb.append(product.toFileCsv());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

}
