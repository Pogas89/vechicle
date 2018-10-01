package com.epam.ivanou4.vehicle.repository;

import com.epam.ivanou4.vehicle.model.Car;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class CarRepositoryImpl implements CarRepository {
    private static final Logger log = getLogger(CarRepositoryImpl.class);

    @Autowired
    private CrudCarRepository repository;

    @Override
    public Car save(Car car) {
        Car save = repository.save(car);
        log.info("save car: " + save);
        return save;
    }

    @Override
    public Car get(String id) {
        Car carById = repository.getCarById(id);
        log.info("get car: " + carById);
        return carById;
    }

    @Override
    public List<Car> getAll() {
        log.info("get all cars");
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        log.info("delete car with id: " + id);
        repository.deleteById(id);
    }
}
