package com.epam.ivanou4.vehicle.repository;

import com.epam.ivanou4.vehicle.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CrudCompanyRepository extends MongoRepository<Company, String> {
    List<Company> findAll();

    Company getById(String id);

    Company save(Company entity);

    void delete(String id);
}
