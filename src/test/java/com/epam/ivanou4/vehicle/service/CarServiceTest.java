package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Car;
import com.epam.ivanou4.vehicle.model.Color;
import com.epam.ivanou4.vehicle.model.Equipment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.epam.ivanou4.vehicle.CarTestData.CAR1;
import static com.epam.ivanou4.vehicle.CarTestData.CAR2;
import static com.epam.ivanou4.vehicle.CarTestData.assertMatch;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {
    @Autowired
    private CarService service;

    @Test
    public void create() {
        Car car = new Car();
        car.setModel("BMW");
        car.setColor(Color.BLACK);
        car.setEquipment(Equipment.LUX);
        service.create(car);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getAll() {
        List<Car> cars = service.getAll();
        assertMatch(cars, CAR1, CAR2);
    }

    @Test
    public void getByModel() {
    }
}