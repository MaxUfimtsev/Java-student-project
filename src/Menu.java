import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<Car> storage;
    Scanner scanner = new Scanner(System.in);

    public Menu() {
        this.storage = new ArrayList<>();
    }

    public void runApp() {
        System.out.println("Добрый день! Выберите способ добавления авто в систему:");
        while (true){
            printMenu();
            String input = scanner.nextLine();
            int command = intParse(input);
            switch (command) {
                case 1 -> addManually();
                case 2 -> loadFromFile();
                case 3 -> processData();
                case 0 -> {
                    System.out.println();
                    System.out.println("Завершение работы программы...");
                    return;
                }
                default -> {
                    System.out.println();
                    System.out.println("Неизвестная команда.");
                    System.out.println();
                }
            }
        }
    }

    private void printMenu() {
        System.out.print("""
                1. - Добавить вручную.
                2. - Загрузить из файла.
                3. - Обработать данные.
                0. - Выйти.
                Ваш ввод:\s""");
    }

    private int intParse(String command) {
        try {
            return Integer.parseInt(command);
        } catch (IllegalArgumentException e){
            System.out.println();
            System.out.println("Неверный формат команды! Попробуйте ввести цифры.");
            return 123;
        }
    }

    private void addManually() {
        System.out.println();
        System.out.println("Введите авто через ';' в виде Марка;Модель;Год;Мощность");
        var input = scanner.nextLine();

        try {
            Car car = CarFileLoader.carParse(input);
            storage.add(car);
            System.out.println("Авто успешно добавлено!");
        } catch (IllegalArgumentException e){
            System.out.println("Авто не добавлено: " + e.getMessage());
        }

        System.out.println();
    }

    private void loadFromFile() {
        System.out.println();
        System.out.println("Введите путь до файла.");
        var filePath = scanner.nextLine();

        var cars = CarFileLoader.loadNewCars(filePath);
        storage.addAll(cars);

        System.out.println(String.format("Загружено автомобилей: %d", cars.size()));
        System.out.println();
    }

    private void processData() {
        if (storage.size() > 0){
            System.out.println();
            System.out.println("Вот все добавленные авто:");
            for (var car: storage){
                System.out.println(car);
            }
        }
        else {
            System.out.println();
            System.out.println("Список пуст!");
            System.out.println();
        }
    }
}
