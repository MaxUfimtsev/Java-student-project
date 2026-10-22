package main.java.service;

import main.java.collection.CarArrayList;
import main.java.model.Car;

import java.util.Comparator;
import java.util.List;

public class CarProcessor {

    public static List<String> toDisplayNames(List<Car> cars) {
        return cars.stream()
                .map(Car::toString)
                .toList();
    }

    public static List<String> toDisplayNames(CarArrayList cars) {
        return cars.stream().map(Car::toString).toList();
    }

    public static List<Car> filterByMinPower(CarArrayList cars, int minPower) {
        return cars.stream()
                .filter(car -> car.getPower() >= minPower)
                .toList();
    }

    public static List<Car> filterByMinYear(CarArrayList cars, int minYear) {
        return cars.stream()
                .filter(car -> car.getYear() >= minYear)
                .toList();
    }

    public static List<Car> sortByYear(CarArrayList cars) {
        return cars.stream()
                .sorted(Comparator.comparingInt(Car::getYear))
                .toList();
    }

    public static List<Car> sortByPower(CarArrayList cars) {
        return cars.stream()
                .sorted(Comparator.comparingInt(Car::getPower))
                .toList();
    }
}
