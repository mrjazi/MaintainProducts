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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
@Data
public class Product implements Serializable {

    private int productId;
    private String productName;
    private BigDecimal productAmount;
    private Date productDate;
    private List<String> productType;
    private String type;
    private int quantity;
    private List<String> productCountry;
    private String country;
    private List<String> productCategory;
    private String category;
    private String productDescription;


    public Product() {

    }

    @PostConstruct
    public void init() {
//        productId = 0;
//        quantity = 0;
        productAmount = new BigDecimal(0);


        productCountry = new ArrayList<String>();
        productCountry.add("Jordan");
        productCountry.add("United States");
        productCountry.add("Canada");
        productCountry.add("France");
        productCountry.add("Germany");
        productCountry.add("Italy");


        productCategory = new ArrayList<String>();
        productCategory.add("Clothes");
        productCategory.add("Food");


        productType = new ArrayList<String>();
        productType.add("New");
        productType.add("Old");


    }

    public Product(int productId, String productName, BigDecimal productAmount, Date productDate, String type, int quantity, String country, String category, String productDescription) {
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

//    @Override
//    public Product clone() {
//        return new Product(getProductId(), getProductName(), getProductAmount(), getProductDate(), getProductType(), getQuantity(), getProductCountry(), getProductCategory(), getProductDescription());
//    }


//    public void onCountryChange() {
//        if (productCountry != null && !productCountry.isEmpty()) {
//            countryPick = productCountry;
//        }
//    }

    public void displayLocation() {
        FacesMessage msg;
        if (category != null && country != null) {
            msg = new FacesMessage("Selected", category + " of " + country);
        }
        else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected.");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
