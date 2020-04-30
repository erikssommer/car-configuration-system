package org.semesteroppgave.models.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.semesteroppgave.models.data.productmodels.Product;

/**
 * Global klasse (hentes fra ApplicationData) som holder på produktlister
 * Listen productList er alle tidligere konfigurerte biler
 * Listen userProductList er liste som inneholder alle produkter opprettet av bruker
 * Objektet selectedProduct er den bilen som skal vises mer informasjon om
 */

public class RegisterProduct {

    private final ObservableList<Product> productList = FXCollections.observableArrayList();
    private final ObservableList<Product> userProductList = FXCollections.observableArrayList();
    private Product selectedProduct;

    public ObservableList<Product> getProductList() {
        return this.productList;
    }

    public void setProductList(Product product) {
        productList.add(product);
    }

    public ObservableList<Product> getUserProductList() {
        return this.userProductList;
    }

    public void setUserProductList(Product product) {
        userProductList.add(product);
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
        for (Product product : userProductList) {
            sb.append(product.toFileCsv());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }
}
