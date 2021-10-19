package com.example.demo.model;

import javax.persistence.*;

@Entity
public class SubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub_name")
    private String subName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "some_entity_id")
    private SomeEntity entity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public SomeEntity getEntity() {
        return entity;
    }

    public void setEntity(SomeEntity entity) {
        this.entity = entity;
    }
}
