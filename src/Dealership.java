import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dealership {
    private List<Car> cars;

    public Dealership() {
        cars = new ArrayList<>();
    }

    public void addCar(Car c) {
        cars.add(c);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void printAllCars() {
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    public double totalEcoTax() {
        double sum = 0;
        for (Car c : cars) sum += c.ecoTax();
        return sum;
    }

    public List<Car> filterByAC(Car.ACType acType) {
        return cars.stream().filter(c -> c.getAcType() == acType).collect(Collectors.toList());
    }
    public List<Car> filterByInterior(Car.Interior interior) {
        return cars.stream().filter(c -> c.getInterior() == interior).collect(Collectors.toList());
    }
    public List<Car> filterByRim(Car.RimType rimType) {
        return cars.stream().filter(c -> c.getRimType() == rimType).collect(Collectors.toList());
    }
}
