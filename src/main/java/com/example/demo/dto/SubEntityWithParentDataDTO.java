package com.example.demo.dto;

public class SubEntityWithParentDataDTO {

    private Long id;
    private String subEntityName;

    private String someEntityName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubEntityName() {
        return subEntityName;
    }

    public void setSubEntityName(String subEntityName) {
        this.subEntityName = subEntityName;
    }

    public String getSomeEntityName() {
        return someEntityName;
    }

    public void setSomeEntityName(String someEntityName) {
        this.someEntityName = someEntityName;
    }
}
