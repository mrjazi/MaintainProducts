package org.primefaces.test;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
@Data
public class Crud implements Serializable {

    private List<Product> products;
    private Product selectedProduct;
    private List<Product> selectedProducts;

    @Inject
    private ProductService productService;

    @PostConstruct
    public void init() {
        this.products = this.productService.getProducts();
        this.selectedProducts = new ArrayList<>();
        this.selectedProduct = new Product();
    }

    public void addProduct() {
        if (selectedProduct != null) {
            selectedProduct.setProductId(products.size() + 1);

            this.products.add(selectedProduct);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));

            this.selectedProduct = new Product();

            PrimeFaces.current().ajax().update("form:msgs", "form:data-table-products");
        }
    }

    public void deleteProduct() {
        if (selectedProduct != null) {
            this.products.remove(selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
            PrimeFaces.current().ajax().update("form:data-table-products");
        }
    }
}
