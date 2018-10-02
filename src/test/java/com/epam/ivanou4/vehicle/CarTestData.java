package com.epam.ivanou4.vehicle;

import com.epam.ivanou4.vehicle.model.Car;
import com.epam.ivanou4.vehicle.model.Color;
import com.epam.ivanou4.vehicle.model.Equipment;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class CarTestData {
    public static String CAR1_ID = "5bb307ee729c6519c84e17b3";
    public static String CAR2_ID = "5bb3094d729c6526d833fe57";

    public static Car CAR1 = new Car();
    public static Car CAR2 = new Car();

    static {
        CAR1.setModel("BMW");
        CAR1.setColor(Color.BLACK);
        CAR1.setEquipment(Equipment.LUX);
        CAR2.setModel("LADA");
        CAR2.setColor(Color.RED);
        CAR2.setEquipment(Equipment.STANDART);
    }

    public static void assertMatch(Car actual, Car expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Car> actual, Iterable<Car> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Car> actual, Car... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }


}
