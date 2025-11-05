public class ElectricCar extends Car {

    private double batteryCapacityKWh;

    public ElectricCar(String make, String model, int year, int engineCapacity, double price,
                       ACType acType, Interior interior, RimType rimType, double batteryCapacityKWh) {
        super(make, model, year, engineCapacity, price, acType, interior, rimType);
        this.batteryCapacityKWh = batteryCapacityKWh;
    }

    public double getBatteryCapacityKWh() { return batteryCapacityKWh; }

    @Override
    public double ecoTax() {
        return batteryCapacityKWh <= 50 ? 0 : 50;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Battery: %.1f kWh, EcoTax: %.2f лв.",
                batteryCapacityKWh, ecoTax());
    }
}
