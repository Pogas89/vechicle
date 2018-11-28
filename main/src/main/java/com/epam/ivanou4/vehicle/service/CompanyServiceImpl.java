package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vechicle.validation.Validation;
import com.epam.ivanou4.vehicle.exception.ValidateException;
import com.epam.ivanou4.vehicle.model.Company;
import com.epam.ivanou4.vehicle.repository.CompanyRepository;
import com.epam.ivanou4.vehicle.to.CompanyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private static final String COMPANY_SCHEMA = "company_shema.json";

    @Autowired
    private CompanyRepository repository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Mapper dozerMapper = new DozerBeanMapper();

    @Override
    public CompanyDTO create(String companyJson) {
        Company company = repository.save(validateCompany(companyJson));
        return dozerMapper.map(company, CompanyDTO.class);
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
    public CompanyDTO get(String id) {
        Company company = repository.get(id);
        if (company!=null) {
            return dozerMapper.map(company, CompanyDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public List<CompanyDTO> getAll() {
        List<Company> companies = repository.getAll();
        List<CompanyDTO> companiesDTO = new ArrayList<>();
        for (Company company : companies) {
            companiesDTO.add(dozerMapper.map(company, CompanyDTO.class));
        }
        return companiesDTO;
    }

    private Company validateCompany(String json) {
        Company company;
        if (new Validation().validate(json, COMPANY_SCHEMA)) {
            try {
                company = objectMapper.readValue(json, Company.class);
                return company;
            } catch (IOException e) {
                throw new ValidateException("Bad or incomplete data");
            }
        } else {
            throw new ValidateException("Bad or incomplete data");
        }
    }
}
