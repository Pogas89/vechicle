package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Subsidiary;
import com.epam.ivanou4.vehicle.repository.SubsidiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubsidiaryServiceImpl implements SubsidiaryService {
    @Autowired
    private SubsidiaryRepository repository;

    @Override
    public Subsidiary create(Subsidiary subsidiary) {
        return repository.save(subsidiary);
    }

    @Override
    public void update(Subsidiary subsidiary) {
        repository.save(subsidiary);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public Subsidiary get(String id) {
        return repository.get(id);
    }

    @Override
    public List<Subsidiary> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Subsidiary> getByCompanyId(String id) {
        return repository.getByCompanyId(id);
    }
}
