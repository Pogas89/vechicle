package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.to.CompanyDTO;

import java.util.List;

public interface CompanyService {
    CompanyDTO create(String companyJson);

    void update(String companyJson);

    void delete(String id);

    CompanyDTO get(String id);

    List<CompanyDTO> getAll();
}
