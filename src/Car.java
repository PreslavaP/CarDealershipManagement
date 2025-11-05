
public abstract class Car implements Extras {
    protected String make;
    protected String model;
    protected int year;
    protected int engineCapacity;
    protected double price;

    protected ACType acType;
    protected Interior interior;
    protected RimType rimType;

    public Car(String make, String model, int year, int engineCapacity, double price,
               ACType acType, Interior interior, RimType rimType) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.engineCapacity = engineCapacity;
        this.price = price;
        this.acType = acType;
        this.interior = interior;
        this.rimType = rimType;
    }

    public abstract double ecoTax();

    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public int getEngineCapacity() { return engineCapacity; }
    public double getPrice() { return price; }

    @Override public ACType getAcType() { return acType; }
    @Override public void setAcType(ACType acType) { this.acType = acType; }

    @Override public Interior getInterior() { return interior; }
    @Override public void setInterior(Interior interior) { this.interior = interior; }

    @Override public RimType getRimType() { return rimType; }
    @Override public void setRimType(RimType rimType) { this.rimType = rimType; }

    @Override
    public double extrasPrice() {
        double sum = 0;
        switch(acType) {
            case MANUAL: sum += 500; break;
            case AUTOMATIC: sum += 900; break;
        }
        switch(interior) {
            case FABRIC: sum += 0; break;
            case LEATHER: sum += 1200; break;
        }
        switch(rimType) {
            case STEEL: sum += 0; break;
            case ALLOY: sum += 600; break;
        }
        return sum;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d) — engine: %dcc, price: %.2f, extras: [AC=%s, Interior=%s, Rims=%s]",
                make, model, year, engineCapacity, price, acType, interior, rimType);
    }
}
