package org.primefaces.test;


import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
@Getter
public class Product implements Serializable {

    private int productId;
    private String productName;
    private BigDecimal productAmount;
    private Date productDate;
    private List<String> productType;
    private int quantity;
    private List<String> productCountry;

    private List<String> productCategory;
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

    public Product(int productId, String productName, BigDecimal productAmount, Date productDate, List<String> productType, int quantity, List<String> productCountry, List<String> productCategory, String productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.productAmount = productAmount;
        this.productDate = productDate;
        this.productType = productType;
        this.quantity = quantity;
        this.productCountry = productCountry;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
    }

//    public void onCountryChange() {
//        if (productCountry != null && !productCountry.isEmpty()) {
//            countryPick = productCountry;
//        }
//    }

}
