import java.util.Comparator;
import java.util.List;

public class CarProcessor {

    public static List<String> toDisplayNames(List<Car> cars) {
        return cars.stream()
                .map(car -> String.format("%s %s (%d г., %d л.с.)%n",
                        car.getMake(), car.getModel(), car.getYear(), car.getPower()))
                .toList();
    }

    public static List<Car> filterByMinPower(List<Car> cars, int minPower) {
        return cars.stream()
                .filter(car -> car.getPower() >= minPower)
                .toList();
    }

    public static List<Car> filterByMinYear(List<Car> cars, int minYear) {
        return cars.stream()
                .filter(car -> car.getYear() >= minYear)
                .toList();
    }

    public static List<Car> sortByYear(List<Car> cars) {
        return cars.stream()
                .sorted(Comparator.comparingInt(Car::getYear))
                .toList();
    }

    public static List<Car> sortByPower(List<Car> cars) {
        return cars.stream()
                .sorted(Comparator.comparingInt(Car::getPower))
                .toList();
    }
}
