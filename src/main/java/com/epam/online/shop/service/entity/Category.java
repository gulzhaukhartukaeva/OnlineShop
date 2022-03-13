package com.epam.online.shop.service.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category {
    private Long id;
    private String name;
    private Integer languageId;
    private Category category;
    private List<Product> products = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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
        Category category1 = (Category) o;
        return Objects.equals(id, category1.id) &&
                Objects.equals(name, category1.name) &&
                Objects.equals(languageId, category1.languageId) &&
                Objects.equals(category, category1.category) &&
                Objects.equals(products, category1.products) &&
                Objects.equals(categories, category1.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, languageId, category, products, categories);
    }
}
