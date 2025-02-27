package org.primefaces.test;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Named
@ApplicationScoped
public class ProductService implements Serializable {
    private List<Product> products;


    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }


}
