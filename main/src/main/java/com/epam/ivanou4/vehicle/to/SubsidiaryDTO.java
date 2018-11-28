package com.epam.ivanou4.vehicle.to;

import java.util.Date;
import java.util.Objects;

public class SubsidiaryDTO {
    private String id;
    private String companyId;
    private String location;
    private Date creationDate;

    public SubsidiaryDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubsidiaryDTO that = (SubsidiaryDTO) o;
        return id.equals(that.id) &&
                companyId.equals(that.companyId) &&
                location.equals(that.location) &&
                creationDate.equals(that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyId, location, creationDate);
    }

    @Override
    public String toString() {
        return "SubsidiaryDTO{" +
                "id='" + id + '\'' +
                ", companyId='" + companyId + '\'' +
                ", location='" + location + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
