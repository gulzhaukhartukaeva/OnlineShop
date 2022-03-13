package com.epam.online.shop.service.entity;

import java.io.InputStream;
import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private String description;
    private Integer discount;
    private Double price;
    private Integer quantity;
    private Category category;
    private Integer languageId;
    private Producer producer;
    private Size size;
    private InputStream uploadingImage;
    private String image;

    public Product() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public InputStream getUploadingImage() {
        return uploadingImage;
    }

    public void setUploadingImage(InputStream uploadingImage) {
        this.uploadingImage = uploadingImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(discount, product.discount) &&
                Objects.equals(price, product.price) &&
                Objects.equals(quantity, product.quantity) &&
                Objects.equals(category, product.category) &&
                Objects.equals(languageId, product.languageId) &&
                Objects.equals(producer, product.producer) &&
                Objects.equals(size, product.size) &&
                Objects.equals(uploadingImage, product.uploadingImage) &&
                Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, discount, price, quantity, category, languageId, producer, size, uploadingImage, image);
    }
}
