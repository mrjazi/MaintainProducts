package org.primefaces.test;


import lombok.Getter;

@Getter
public enum ProductType {


    NEW("New"),
    OLD("Old");

    private String type;


    ProductType(String type) {
        this.type = type;
    }
}
