package com.example.demo.dto;

import java.util.List;

public class SomeEntityDTO {

    private Long id;
    private String name;
    private List<SubEntityDTO> subEntities;

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

    public List<SubEntityDTO> getSubEntities() {
        return subEntities;
    }

    public void setSubEntities(List<SubEntityDTO> subEntities) {
        this.subEntities = subEntities;
    }
}
