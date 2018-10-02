package com.epam.ivanou4.vehicle.repository;

import com.epam.ivanou4.vehicle.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CrudCarRepository extends MongoRepository<Car, String> {
    List<Car> findByModel(String model);

    Car getCarById(String id);

    void deleteById(String id);
}
