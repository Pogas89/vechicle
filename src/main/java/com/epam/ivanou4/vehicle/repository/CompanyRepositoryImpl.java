package com.epam.ivanou4.vehicle.repository;

import com.epam.ivanou4.vehicle.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {
    @Autowired
    CrudCompanyRepository repository;

    @Override
    public Company save(Company company) {
        return repository.save(company);
    }

    @Override
    public Company get(String id) {
        return repository.getById(id);
    }

    @Override
    public List<Company> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}
