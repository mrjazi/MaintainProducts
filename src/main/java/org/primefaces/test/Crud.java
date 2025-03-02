package org.primefaces.test;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Getter
@Named
@ViewScoped
@Data

public class Crud implements Serializable {

    private List<Product> products;
    @Setter
    private Product selectedProduct;
    private List<String> countries;
    private List<String> categories;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        selectedProduct = new Product();
        countries = Arrays.asList("USA", "Canada", "Germany", "France", "Jordan");
        categories = Arrays.asList("Electronics", "Clothing", "Books");
        products.add(new Product(
                "123456789",
                "Book",
                new BigDecimal("19.99"),
                "uuu",
                "New",
                2,
                "Jo",
                "Books",
                "A great book."
        ));
    }

    public void addProduct() {
        if (selectedProduct != null) {

            if (products.stream().anyMatch(i -> i.getProductId().equals(selectedProduct.getProductId()))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Product with the same ID already exists."));
                return;
            }

            products.add(selectedProduct.clone());

            System.out.println("---------------------->>>>>>>>>>>>" + selectedProduct);
            System.out.println("-------------product--------->>>>>>>>>>>>" + products);

            selectedProduct = new Product();
            selectedProduct.init();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Product added successfully."));
        }
    }
}