package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Company;

import java.util.List;

public interface CompanyService {
    Company create(Company company);

    void update(Company company);

    void delete(String id);

    Company get(String id);

    List<Company> getAll();
}
