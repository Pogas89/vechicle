package com.epam.ivanou4.vehicle.model;


import org.springframework.data.annotation.Id;

public abstract class AbstractBaseEntity {
    @Id
    protected String id;

    public AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(String id) {
        this.id = id;
    }

    public final String getId() {
        return id;
    }

    public final void setId(String id) {
        this.id = id;
    }
}
