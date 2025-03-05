package org.primefaces.test;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;
import org.primefaces.PrimeFaces;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private List<Product> selectedProducts;

    private List<Product> productToView;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        selectedProduct = new Product();
        this.selectedProducts = new ArrayList<Product>();
        productToView = new ArrayList<>();

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
                selectedProduct = new Product();
                selectedProduct.init();
                return;
            }

//            DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            LocalDate today = selectedProduct.getProductDate();
//            String formatDate = today.format(formatPattern);
////            LocalDate
////                    .parse( "2022-05-12" )
////                    .format(
////                            DateTimeFormatter.ofPattern( "dd-MM-uuuu" )
////                    )
//            selectedProduct.setProductDate(LocalDate.parse(today.format(formatPattern), formatPattern));



            DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate today = selectedProduct.getProductDate();
            System.out.println("Formatted Date before: " + selectedProduct.getProductDate());

            String formatDate = today.format(formatPattern);
            System.out.println("Formatted Date: " + formatDate);

            LocalDate parsedDate = LocalDate.parse(formatDate, formatPattern);

            selectedProduct.setDate(formatDate);

            System.out.println("Formatted Date after: " + selectedProduct.getProductDate());


            System.out.println("---------------->>>>>>>> " + selectedProduct.getProductDate());

            products.add(selectedProduct.clone());

            System.out.println("---------------------->>>>>>>>>>>>" + selectedProduct);
            System.out.println("-------------product--------->>>>>>>>>>>>" + products);

            selectedProduct = new Product();
            selectedProduct.init();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Product added successfully."));
        }
    }


    public void saveProduct() {
        if (this.selectedProduct.getProductId() != null) {

            DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate today = selectedProduct.getProductDate();
            String formatDate = today.format(formatPattern);

            this.products.add(this.selectedProduct);

            this.products.remove(this.selectedProduct);
            this.selectedProducts.remove(this.selectedProduct);
            selectedProduct = new Product();
            selectedProduct.init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Product saved successfully."));

            PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
            PrimeFaces.current().ajax().update("form:msgs", "form");
        }
    }

    public void deleteProduct() {
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
//        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product " + this.selectedProduct.getProductName() + " removed"));
        PrimeFaces.current().ajax().update("form:msgs", "form:data-table-products");
    }

//    public void deleteSelectedProducts() {
//        if (this.selectedProducts != null && !this.selectedProducts.isEmpty()) {
//            this.products.removeAll(this.selectedProducts);
//            this.selectedProducts.clear();
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage("Products Removed"));
//            PrimeFaces.current().ajax().update("form:msgs", "form:data-table-products");
//        }
//    }


    public String viewProduct() {
        this.productToView = new ArrayList<>();

        System.out.println("------------------------->>> " + selectedProduct);

        this.productToView.add(selectedProduct);

        System.out.println("-----------product to view----------->>>>>>>>>>" + productToView);

        return productToView.toString();
    }

    public void deleteAllProducts() {
        System.out.println("deleteAllProducts");
        System.out.println(products.size());

        for (Product product : products) {
            products.remove(product);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products cleared"));
        PrimeFaces.current().ajax().update("form:msgs", "form:data-table-products");
    }




}