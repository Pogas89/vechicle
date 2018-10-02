package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Car;

import java.util.List;

public interface CarService {
    Car create(Car car);

    void update(Car car);

    void delete(String id);

    Car get(String id);

    List<Car> getAll();

    List<Car> getByModel(String model);
}
