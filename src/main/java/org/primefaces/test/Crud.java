package org.primefaces.test;


import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
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


    public void add() {
        this.selectedProducts.add(selectedProduct);
        System.out.println(selectedProducts);
    }

    public void save() {
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void delete() {
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

}

