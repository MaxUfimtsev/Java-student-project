import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class CarFileLoader {

    public static ArrayList<Car> LoadNewCars(String path){

        var cars = new ArrayList<Car>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;
            var lineNumber = 0;
            while((line = reader.readLine()) != null){
                lineNumber++;
                if (line.isBlank()){
                    continue;
                }
                try {
                    cars.add(CarParse(line));
                } catch (IllegalArgumentException e) {
                    System.out.println("Строка " + lineNumber + " пропущена. " + e.getMessage());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать файл: " + path);
        }

        return cars;
    }

    private static Car CarParse(String line) {
        var carParts = Arrays.stream(line.split(";"))
                .map(item -> item.trim())
                .toArray(String[]::new);

        if (carParts.length != 4){
            throw new IllegalArgumentException("Ожидалось 4 поля, найдено " + carParts.length);
        }

        return new Car(carParts[0], carParts[1], Integer.parseInt(carParts[2]), Integer.parseInt(carParts[3]));
    }
}