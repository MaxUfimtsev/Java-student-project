import java.time.Year;

public class Car {
    private String make;
    private String model;
    private int year;
    private int power;

    public Car(String make, String model, int year, int power){
        setMake(make);
        setModel(model);
        setPower(power);
        setYear(year);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        if (make.isBlank()){
            throw new IllegalArgumentException("Пустая строка бренда.");
        }
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model.isBlank()){
            throw new IllegalArgumentException("Пустая строка модели.");
        }
        this.model = model;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (power <= 0){
            throw new IllegalArgumentException("Мощность должна быть больше 0.");
        }
        this.power = power;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year <= 1885 || year > Year.now().getValue()+1){
            throw new IllegalArgumentException("Неверное значение года производства авто.");
        }
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("Марка: %s | Модель: %s | Год: %d | Мощность: %d", make, model, year, power);
    }
}
