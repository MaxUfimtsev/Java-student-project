package main.java.model;

import java.time.Year;

public class Car {
    private String make;
    private String model;
    private int year;
    private int power;
    private String color;

    public Car(String make, String model, int year, int power, String color){
        this.make = make;
        this.model = model;
        this.year = year;
        this.power = power;
        this.color = color;
        validate();
    }

    public void validate() {
        setMake(make);
        setModel(model);
        setYear(year);
        setPower(power);
        setColor(color);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        if (make == null || make.isBlank()){
            throw new IllegalArgumentException("Пустая строка бренда.");
        }
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.isBlank()){
            throw new IllegalArgumentException("Пустая строка модели.");
        }
        this.model = model;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (power <= 0) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null || color.isBlank()){
            throw new IllegalArgumentException("Цвет не может быть пустым");
        }
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d г., %d л.с.) %s", make, model, year, power, color);
    }
}
