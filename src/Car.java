public class Car {
    private String model;
    private int year;
    private int power;

    public Car(String model, int year, int power){
        this.model = model;
        this.power = power;
        this.year = year;
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
        return String.format("Модель: %s | Год: %d | Мощность: %d", model, year, power);
    }
}
