package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Company;
import com.epam.ivanou4.vehicle.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository repository;

    @Override
    public Company create(Company company) {
        return repository.save(company);
    }

    @Override
    public void update(Company company) {
        repository.save(company);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public Company get(String id) {
        return repository.get(id);
    }

    @Override
    public List<Company> getAll() {
        return repository.getAll();
    }
}
