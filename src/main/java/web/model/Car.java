package web.model;

public class Car {
    private String model;
    private String mark;
    private int numberOfWheels;

    public Car() {

    }

    public Car(String model, String mark, int numberOfWheels) {
        this.model = model;
        this.mark = mark;
        this.numberOfWheels = numberOfWheels;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", mark='" + mark + '\'' +
                ", numberOfWheels=" + numberOfWheels +
                '}';
    }
}
