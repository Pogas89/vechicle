package com.epam.ivanou4.vehicle.repository;

import com.epam.ivanou4.vehicle.model.Subsidiary;

import java.util.List;

public interface SubsidiaryRepository {
    Subsidiary save(Subsidiary subsidiary);

    Subsidiary get(String id);

    List<Subsidiary> getAll();

    void delete(String id);

    List<Subsidiary> getByCompanyId(String id);
}
