public class Car {
    private String make;
    private String model;
    private int year;
    private int power;

    public Car(String make, String model, int year, int power){
        this.make = make;
        this.model = model;
        this.power = power;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("Марка: %s | Модель: %s | Год: %d | Мощность: %d", make, model, year, power);
    }
}
