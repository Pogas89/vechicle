package com.epam.ivanou4.vehicle.repository;

import com.epam.ivanou4.vehicle.model.Company;

import java.util.List;

public interface CompanyRepository {
    Company save(Company company);

    Company get(String id);

    List<Company> getAll();

    void delete(String id);
}
