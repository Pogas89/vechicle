package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vechicle.validation.Validation;
import com.epam.ivanou4.vehicle.exception.ValidateException;
import com.epam.ivanou4.vehicle.model.Subsidiary;
import com.epam.ivanou4.vehicle.repository.SubsidiaryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SubsidiaryServiceImpl implements SubsidiaryService {
    public static final String SUBSIDIARY_SCHEMA = "subsidiary_shema.json";

    @Autowired
    private SubsidiaryRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Subsidiary create(String subsidiaryJson) {
        return repository.save(validateSubsidiary(subsidiaryJson));
    }

    @Override
    public void update(String subsidiaryJson) {
        repository.save(validateSubsidiary(subsidiaryJson));
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

    private Subsidiary validateSubsidiary(String json) {
        Subsidiary subsidiary;
        if (new Validation().validate(json, SUBSIDIARY_SCHEMA)) {
            try {
                subsidiary = mapper.readValue(json, Subsidiary.class);
                return subsidiary;
            } catch (IOException e) {
                throw new ValidateException("Bad or incomplete data");
            }
        } else {
            throw new ValidateException("Bad or incomplete data");
        }
    }
}
