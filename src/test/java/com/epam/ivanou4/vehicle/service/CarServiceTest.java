package com.epam.ivanou4.vehicle.service;

import com.epam.ivanou4.vehicle.model.Car;
import com.epam.ivanou4.vehicle.model.Color;
import com.epam.ivanou4.vehicle.model.Equipment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        service.getAll();
    }

    @Test
    public void getByModel() {
    }
}