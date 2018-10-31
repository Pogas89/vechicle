package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Company;

import java.util.List;

public interface CompanyService {
    Company create(String companyJson);

    void update(String companyJson);

    void delete(String id);

    Company get(String id);

    List<Company> getAll();
}
