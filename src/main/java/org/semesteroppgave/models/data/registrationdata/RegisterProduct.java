package org.semesteroppgave.models.data.registrationdata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.semesteroppgave.models.data.productmodels.Product;

/**
 * Global class (retrieved from ApplicationData) which keeps on product lists
 * The productList list is all previously configured cars
 * The userProductList list is the list that contains all user-created products
 * The selectedProduct object is the car for which more information is displayed
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
