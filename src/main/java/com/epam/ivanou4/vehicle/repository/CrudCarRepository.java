package com.epam.ivanou4.vehicle.repository;

import com.epam.ivanou4.vehicle.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrudCarRepository extends MongoRepository<Car, String> {
    Car findByModel(String model);

    Car getCarById(String id);

    void deleteById(String id);
}
