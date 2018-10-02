package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Car;
import com.epam.ivanou4.vehicle.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private static final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    CarRepository repository;

    @Override
    public Car create(Car car) {
        log.debug("service create car: " + car);
        return repository.save(car);
    }

    @Override
    public void update(Car car) {
        log.debug("service update car: " + car);
        repository.save(car);
    }

    @Override
    public void delete(String id) {
        log.debug("service delete car with id: " + id);
        repository.delete(id);
    }

    @Override
    public Car get(String id) {
        log.debug("service get car with id: " + id);
        return repository.get(id);
    }

    @Override
    public List<Car> getAll() {
        log.debug("service get all cars");
        return repository.getAll();
    }

    @Override
    public List<Car> getByModel(String model) {
        log.debug("service get car by model: " + model);
        return repository.findByModel(model);
    }
}
