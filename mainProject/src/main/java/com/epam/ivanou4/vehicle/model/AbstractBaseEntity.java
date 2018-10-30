package com.epam.ivanou4.vehicle.model;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public abstract class AbstractBaseEntity {
    @Id
    private String id;

    public final String getId() {
        return id;
    }

    public final void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractBaseEntity)) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
