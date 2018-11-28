package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.to.SubsidiaryDTO;

import java.util.List;

public interface SubsidiaryService {
    SubsidiaryDTO create(String subsidiaryJson);

    void update(String subsidiaryJson);

    void delete(String id);

    SubsidiaryDTO get(String id);

    List<SubsidiaryDTO> getAll();

    List<SubsidiaryDTO> getByCompanyId(String id);
}
