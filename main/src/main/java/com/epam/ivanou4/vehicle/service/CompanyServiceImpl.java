package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vechicle.validation.Validation;
import com.epam.ivanou4.vehicle.exception.ValidateException;
import com.epam.ivanou4.vehicle.model.Company;
import com.epam.ivanou4.vehicle.repository.CompanyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    public static final String COMPANY_SCHEMA = "company_shema.json";

    @Autowired
    private CompanyRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Company create(String companyJson) {
        return repository.save(validateCompany(companyJson));
    }

    @Override
    public void update(String companyJson) {
        repository.save(validateCompany(companyJson));
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

    private Company validateCompany(String json) {
        Company company;
        if (new Validation().validate(json, COMPANY_SCHEMA)) {
            try {
                company = mapper.readValue(json, Company.class);
                return company;
            } catch (IOException e) {
                throw new ValidateException("Bad or incomplete data");
            }
        } else {
            throw new ValidateException("Bad or incomplete data");
        }
    }
}
