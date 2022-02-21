package com.epam.onlineShopService.entity;

import java.util.ArrayList;
import java.util.List;

public class Producer {
    private Long id;
    private String name;
    private Integer languageId;
    private List<Product> products = new ArrayList<>();

    public Producer() {
    }

    public Producer(Long id, String name, Integer languageID, List<Product> products) {
        this.id = id;
        this.name = name;
        this.languageId = languageID;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
