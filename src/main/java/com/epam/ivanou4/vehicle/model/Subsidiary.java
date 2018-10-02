package com.epam.ivanou4.vehicle.model;

import java.util.Date;
import java.util.Objects;

public class Subsidiary extends AbstractBaseEntity {
    private String companyId;
    private String location;
    private Date creationDate;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Subsidiary{" +
                "companyId='" + companyId + '\'' +
                ", location='" + location + '\'' +
                ", creationDate=" + creationDate +
                ", id='" + getId() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subsidiary)) return false;
        if (!super.equals(o)) return false;
        Subsidiary that = (Subsidiary) o;
        return Objects.equals(getCompanyId(), that.getCompanyId()) &&
                Objects.equals(getLocation(), that.getLocation()) &&
                Objects.equals(getCreationDate(), that.getCreationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCompanyId(), getLocation(), getCreationDate());
    }
}
