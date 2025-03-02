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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

@Getter
@Named
@ViewScoped
@Data
public class Crud implements Serializable {

    private List<Product> products;
    private Product selectedProduct;

    private List<String> countries;
    private List<String> categories;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        selectedProduct = new Product();


        countries = Arrays.asList("USA", "Canada", "Germany", "Jordan");
        categories = Arrays.asList("Electronics", "Clothing", "Books");
    }

    public void addProduct() {
        if (selectedProduct != null) {
//            for (Product product : products) {
//                if (product.equals(selectedProduct.getProductId())) {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Product with the same ID already exists."));
//                    return;
//                }
//            }

            if (products.stream().anyMatch(i -> i.getProductId().equals(selectedProduct.getProductId()))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Product with this ID already exists."));
                return;
            }

            DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate today = selectedProduct.getProductDate();
            String formatDate = today.format(formatPattern);
//            LocalDate
//                    .parse( "2022-05-12" )
//                    .format(
//                            DateTimeFormatter.ofPattern( "dd-MM-uuuu" )
//                    )
            selectedProduct.setProductDate(LocalDate.parse(today.format(formatPattern), formatPattern));


            System.out.println("---------------->>>>>>>> "+selectedProduct.getProductDate());

            products.add(selectedProduct.clone());

            System.out.println("---------------------->>>>>>>>>>>>" + selectedProduct);
            System.out.println("-------------product--------->>>>>>>>>>>>" + products);

            selectedProduct = new Product();
            selectedProduct.init();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Product added successfully."));
        }
    }
}