package com.epam.ivanou4.vehicle.repository;

import com.epam.ivanou4.vehicle.model.Subsidiary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubsidiaryRepositoryImpl implements SubsidiaryRepository {
    @Autowired
    CrudSubsidiaryRepository repository;

    @Override
    public Subsidiary save(Subsidiary subsidiary) {
        return repository.save(subsidiary);
    }

    @Override
    public Subsidiary get(String id) {
        return repository.getById(id);
    }

    @Override
    public List<Subsidiary> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Subsidiary> getByCompanyId(String id) {
        return repository.getByCompanyId(id);
    }
}
