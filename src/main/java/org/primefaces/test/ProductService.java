package org.primefaces.test;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.*;

@Named
@ApplicationScoped
public class ProductService implements Serializable {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        Date productDate = new Date();

        products.add(new Product(
                1,
                "Book",
                new BigDecimal("19.99"),
                productDate,
                "New",
                2,
                "Jo",
                "Books",
                "A great book."
        ));
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }





}
