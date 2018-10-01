package com.epam.ivanou4.vehicle.repository;

import com.epam.ivanou4.vehicle.model.Car;

import java.util.List;

public interface CarRepository {
    Car save(Car car);

    Car get(String id);

    List<Car> getAll();

    void delete(String id);
}
