package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vechicle.validation.Validation;
import com.epam.ivanou4.vehicle.exception.ValidateException;
import com.epam.ivanou4.vehicle.model.Subsidiary;
import com.epam.ivanou4.vehicle.repository.SubsidiaryRepository;
import com.epam.ivanou4.vehicle.to.SubsidiaryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubsidiaryServiceImpl implements SubsidiaryService {
    private static final String SUBSIDIARY_SCHEMA = "subsidiary_shema.json";

    @Autowired
    private SubsidiaryRepository repository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Mapper dozerMapper = new DozerBeanMapper();

    @Override
    public SubsidiaryDTO create(String subsidiaryJson) {
        return dozerMapper.map(repository.save(validateSubsidiary(subsidiaryJson)), SubsidiaryDTO.class);
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
    public SubsidiaryDTO get(String id) {
        Subsidiary subsidiary = repository.get(id);
        if (subsidiary != null) {
            return dozerMapper.map(subsidiary, SubsidiaryDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public List<SubsidiaryDTO> getAll() {
        List<Subsidiary> subsidiaries = repository.getAll();
        return convertToDTO(subsidiaries);
    }

    @Override
    public List<SubsidiaryDTO> getByCompanyId(String id) {
        List<Subsidiary> subsidiaries = repository.getByCompanyId(id);
        return convertToDTO(subsidiaries);
    }

    private Subsidiary validateSubsidiary(String json) {
        Subsidiary subsidiary;
        if (new Validation().validate(json, SUBSIDIARY_SCHEMA)) {
            try {
                subsidiary = objectMapper.readValue(json, Subsidiary.class);
                return subsidiary;
            } catch (IOException e) {
                throw new ValidateException("Bad or incomplete data");
            }
        } else {
            throw new ValidateException("Bad or incomplete data");
        }
    }

    private List<SubsidiaryDTO> convertToDTO(List<Subsidiary> subsidiaries) {
        List<SubsidiaryDTO> subsidiaryDTOS = new ArrayList<>();
        for (Subsidiary subsidiary : subsidiaries) {
            subsidiaryDTOS.add(dozerMapper.map(subsidiary, SubsidiaryDTO.class));
        }
        return subsidiaryDTOS;
    }
}
