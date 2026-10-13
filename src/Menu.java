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
        System.out.println("Добрый день! Выберите действие:");
        while (true){
            printMenu();
            int command = intParse("Ваш ввод: ");
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
                """);
    }

    private void printProcessMenu() {
        System.out.print("""
                1. - Вывести все.
                2. - Фильтровать.
                3. - Сортировать.
                0. - Выйти.
                """);
    }

    private void printChooseMenu() {
        System.out.print("""
                1. - По году.
                2. - По мощности.
                0. - Выйти.
                """);
    }

    private void printFilterNumber(String command) {
        System.out.printf("""
                %s
                """, command);
    }

    private int intParse(String command) {
        while (true){
            System.out.print(command);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e){
                System.out.println();
                System.out.println("Неверный формат команды! Попробуйте ввести цифры.");
            }
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
        var filePath = scanner.nextLine().trim();

        List<Car> cars = null;
        if (filePath.toLowerCase().endsWith(".txt")) {
            cars = CarFileLoader.loadNewCars(filePath);
        } else if (filePath.toLowerCase().endsWith(".json")) {
            cars = CarJsonLoader.loadNewCars(filePath);
        }
        else {
            System.out.println("Поддерживаются только файлы .txt и .json");
            System.out.println();
            return;
        }

        storage.addAll(cars);

        System.out.printf("Загружено автомобилей: %d%n", cars.size());
        System.out.println();
    }

    private void processData() {
        if (!storage.isEmpty()){
            System.out.println();
            System.out.println("Выберите действие:");

            while (true){
                printProcessMenu();
                int command = intParse("Ваш ввод: ");
                switch (command) {
                    case 1 -> showFormattedCarList(CarProcessor.toDisplayNames(storage));
                    case 2 -> filterCars();
                    case 3 -> sortCars();
                    case 0 -> {
                        System.out.println();
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
        else {
            System.out.println("Список пуст!");
            return;
        }
    }

    private void showFormattedCarList(List<String> cars) {
        if (cars.isEmpty()){
            System.out.println("Ничего не найдено.");
            return;
        }

        System.out.println();
        System.out.println("Ниже список авто:");
        for (var car: cars){
            System.out.println(car);
        }
        System.out.println();
    }

    private void filterCars() {
        printChooseMenu();
        System.out.println();
        int filterCommand = intParse("Ваш ввод: ");
        switch (filterCommand) {
            case 1 -> {
                printFilterNumber("Какой минимальный год выпуска?");
                int filterNumber = intParse("Введите число: ");
                var cars = CarProcessor.filterByMinYear(storage, filterNumber);
                printCars(cars);
            }
            case 2 -> {
                printFilterNumber("Какая минимальная мощность?");
                int filterNumber = intParse("Введите число: ");
                var cars = CarProcessor.filterByMinPower(storage, filterNumber);
                printCars(cars);
            }
            case 0 -> {
                return;
            }
            default -> {
                System.out.println();
                System.out.println("Неизвестная команда.");
                System.out.println();
            }
        }
    }

    private void sortCars() {
        printChooseMenu();
        System.out.println();
        int sortCommand = intParse("Ваш ввод: ");
        switch (sortCommand) {
            case 1 -> {
                var cars = CarProcessor.sortByYear(storage);
                printCars(cars);
            }
            case 2 -> {
                var cars = CarProcessor.sortByPower(storage);
                printCars(cars);
            }
            case 0 -> {
                return;
            }
            default -> {
                System.out.println();
                System.out.println("Неизвестная команда.");
                System.out.println();
            }
        }
    }

    private void printCars(List<Car> cars) {
        var strCars = CarProcessor.toDisplayNames(cars);
        showFormattedCarList(strCars);
    }
}
