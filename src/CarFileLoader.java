import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarFileLoader {

    public static List<Car> loadNewCars(String path){

        var cars = new ArrayList<Car>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))) {

            String line;
            var lineNumber = 0;
            while((line = reader.readLine()) != null){
                lineNumber++;
                if (line.isBlank()){
                    continue;
                }
                try {
                    cars.add(carParse(line));
                } catch (IllegalArgumentException e) {
                    System.out.println("Строка " + lineNumber + " пропущена. " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл: " + path);
        }

        return cars;
    }

    public static Car carParse(String line) {
        var carParts = Arrays.stream(line.split("[;,]"))
                .map(item -> item.trim())
                .toArray(String[]::new);

        if (carParts.length != 5){
            throw new IllegalArgumentException("Ожидалось 5 поля, найдено " + carParts.length);
        }

        try {
            var car = new Car(
                    carParts[0],
                    carParts[1],
                    Integer.parseInt(carParts[2]),
                    Integer.parseInt(carParts[3]),
                    carParts[4]);
            car.validate();
            return car;
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Год и мощность должны быть числами.");
        }
    }
}