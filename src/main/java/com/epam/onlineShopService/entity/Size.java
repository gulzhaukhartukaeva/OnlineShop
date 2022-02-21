package com.epam.onlineShopService.entity;

import java.util.ArrayList;
import java.util.List;

public class Size {
    private Long id;
    private String name;
    private Integer languageId;

    public Size() {
    }

    public Size(Long id, String name, Integer languageID) {
        this.id = id;
        this.name = name;
        this.languageId = languageID;
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
}
