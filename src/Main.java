public class Main {
    public static void main(String[] args) {

        var a = CarFileLoader.loadNewCars("data/cars.txt");

        a.forEach(System.out::println);
    }
}