package org.primefaces.test;


import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Named
@ViewScoped
@Data
public class Product implements Serializable {

    private String productId;
    private String productName;
    private BigDecimal productAmount;
    private LocalDate productDate;
    private String type;
    private int quantity;
    private String country;
    private String category;
    private String productDescription;


    public Product() {
    }

    @PostConstruct
    public void init() {

    }

    public Product(String productId, String productName, BigDecimal productAmount, LocalDate productDate, String type, int quantity, String country, String category, String productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.productAmount = productAmount;
        this.productDate = productDate;
        this.type = type;
        this.quantity = quantity;
        this.country = country;
        this.category = category;
        this.productDescription = productDescription;
    }

    @Override
    public Product clone() {
        return new Product(getProductId(), getProductName(), getProductAmount(), getProductDate(), getType(), getQuantity(), getCountry(), getCategory(), getProductDescription());
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        if (productId == null) {
            return other.productId == null;
        }
        else {
            return productId.equals(other.productId);
        }
    }
}
