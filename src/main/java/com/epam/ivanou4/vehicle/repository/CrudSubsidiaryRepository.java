package com.epam.ivanou4.vehicle.repository;

import com.epam.ivanou4.vehicle.model.Subsidiary;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CrudSubsidiaryRepository extends MongoRepository<Subsidiary, String> {
    Subsidiary save(Subsidiary entity);

    Subsidiary getById(String id);

    List<Subsidiary> findAll();

    void deleteById(String id);

    List<Subsidiary> getByCompanyId(String companyId);
}
