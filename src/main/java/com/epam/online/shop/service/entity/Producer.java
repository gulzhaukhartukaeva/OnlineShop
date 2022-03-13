package com.epam.online.shop.service.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(id, producer.id) &&
                Objects.equals(name, producer.name) &&
                Objects.equals(languageId, producer.languageId) &&
                Objects.equals(products, producer.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, languageId, products);
    }
}
