package com.epam.online.shop.service.entity;

import java.util.Objects;

public class Role {
    private Long id;
    private String name;
    private Integer languageId;

    public Role() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(id, role1.id) &&
                Objects.equals(name, role1.name) &&
                Objects.equals(languageId, role1.languageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, languageId);
    }
}
