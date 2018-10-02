package com.epam.ivanou4.vehicle.model;

import java.util.Date;

public class Subsidiary extends AbstractBaseEntity {
    private Company company;
    private String location;
    private Date creationDate;

    public Subsidiary() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
                "company=" + company +
                ", location='" + location + '\'' +
                ", creationDate=" + creationDate +
                ", id='" + id + '\'' +
                '}';
    }
}
