package com.epam.ivanou4.vehicle.model;

import java.util.Date;

public class Subsidiary extends AbstractBaseEntity {
    private String companyId;
    private String location;
    private Date creationDate;

    public Subsidiary() {
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
    public String toString() {
        return "Subsidiary{" +
                "companyId='" + companyId + '\'' +
                ", location='" + location + '\'' +
                ", creationDate=" + creationDate +
                ", id='" + id + '\'' +
                '}';
    }
}
