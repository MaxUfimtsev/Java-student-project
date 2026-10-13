import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CarJsonLoader {

    private static Gson gson = new Gson();

    public static List<Car> loadNewCars(String path) {

        var cars = new ArrayList<Car>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))){
            JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
            int index = 0;
            for (JsonElement element: array){
                index++;
                try {
                    cars.add(carParse(element.getAsJsonObject()));
                } catch (JsonParseException e){
                    System.out.println("Запись " + index + " пропущена. Неверный формат данных.");
                } catch (IllegalArgumentException | IllegalStateException e){
                    System.out.println("Запись " + index + " пропущена. " + e.getMessage());
                }
            }
        } catch (IOException | InvalidPathException e) {
            System.out.println("Не удалось прочитать файл: " + path);
        } catch (JsonParseException | IllegalStateException e) {
            System.out.println("Файл содержит некорректный JSON: " + e.getMessage());
        }

        return cars;
    }

    private static Car carParse(JsonObject object) {

        var car = gson.fromJson(object, Car.class);
        car.validate();
        return car;
    }
}
