import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Dealership dealership = new Dealership();

        System.out.println("=== Въвеждане на автомобили ===");

        // 5 дизелови коли
        for (int i = 0; i < 1; i++) {
            System.out.println("\nВъведи дизелов автомобил #" + (i + 1));
            dealership.addCar(inputDieselCar());
        }

        // 5 бензинови коли
        for (int i = 0; i < 1; i++) {
            System.out.println("\nВъведи бензинов автомобил #" + (i + 1));
            dealership.addCar(inputPetrolCar());
        }

        // 5 електрически коли
        for (int i = 0; i < 1; i++) {
            System.out.println("\nВъведи електрически автомобил #" + (i + 1));
            dealership.addCar(inputElectricCar());
        }

        System.out.println("\n=== Всички въведени коли ===");
        dealership.printAllCars();

        System.out.printf("\nОбща сума на еко такси: %.2f лв.\n", dealership.totalEcoTax());

        // Филтриране по екстра
        filterByExtrasMenu(dealership);
    }

    // ===== Общ метод за въвеждане на основна информация =====
    private static Object[] readBaseCarInfo() {
        String make = readNonEmpty("Марка: ");
        String model = readNonEmpty("Модел: ");
        int year = readPositiveInt("Година: ");
        int engine = readPositiveInt("Обем двигател (куб. см): ");
        double price = readNonNegativeDouble();

        Car.ACType ac = chooseACType();
        Car.Interior interior = chooseInterior();
        Car.RimType rim = chooseRim();

        return new Object[]{make, model, year, engine, price, ac, interior, rim};
    }

    // ===== Въвеждане на коли по тип =====

    private static DieselCar inputDieselCar() {
        while (true) {
            try {
                Object[] base = readBaseCarInfo();
                return new DieselCar(
                        (String) base[0], (String) base[1], (int) base[2],
                        (int) base[3], (double) base[4],
                        (Car.ACType) base[5], (Car.Interior) base[6], (Car.RimType) base[7]
                );
            } catch (IllegalArgumentException e) {
                System.out.println("Грешка: " + e.getMessage());
            }
        }
    }

    private static PetrolCar inputPetrolCar() {
        while (true) {
            try {
                Object[] base = readBaseCarInfo();
                return new PetrolCar(
                        (String) base[0], (String) base[1], (int) base[2],
                        (int) base[3], (double) base[4],
                        (Car.ACType) base[5], (Car.Interior) base[6], (Car.RimType) base[7]
                );
            } catch (IllegalArgumentException e) {
                System.out.println("Грешка: " + e.getMessage());
            }
        }
    }

    private static ElectricCar inputElectricCar() {
        while (true) {
            try {
                Object[] base = readBaseCarInfo();
                double battery = readPositiveDouble();
                return new ElectricCar(
                        (String) base[0], (String) base[1], (int) base[2],
                        (int) base[3], (double) base[4],
                        (Extras.ACType) base[5], (Car.Interior) base[6], (Car.RimType) base[7],
                        battery
                );
            } catch (IllegalArgumentException e) {
                System.out.println("Грешка: " + e.getMessage());
            }
        }
    }

    // ===== Помощни методи =====

    private static String readNonEmpty(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) throw new IllegalArgumentException("Полето не може да е празно!");
        return input;
    }

    private static int readPositiveInt(String prompt) {
        System.out.print(prompt);
        int value = Integer.parseInt(scanner.nextLine());
        if (value <= 0) throw new IllegalArgumentException("Числото трябва да е положително!");
        return value;
    }

    private static double readPositiveDouble() {
        System.out.print("Капацитет на батерия (kWh): ");
        double value = Double.parseDouble(scanner.nextLine());
        if (value <= 0) throw new IllegalArgumentException("Числото трябва да е положително!");
        return value;
    }

    private static double readNonNegativeDouble() {
        System.out.print("Цена: ");
        double value = Double.parseDouble(scanner.nextLine());
        if (value < 0) throw new IllegalArgumentException("Числото не може да е отрицателно!");
        return value;
    }

    // ===== Избор на екстри =====

    private static Car.ACType chooseACType() {
        System.out.print("Тип климатик (1=MANUAL, 2=AUTOMATIC): ");
        int choice = Integer.parseInt(scanner.nextLine());
        return choice == 2 ? Car.ACType.AUTOMATIC : Car.ACType.MANUAL;
    }

    private static Car.Interior chooseInterior() {
        System.out.print("Тип салон (1=FABRIC, 2=LEATHER): ");
        int choice = Integer.parseInt(scanner.nextLine());
        return choice == 2 ? Car.Interior.LEATHER : Car.Interior.FABRIC;
    }

    private static Car.RimType chooseRim() {
        System.out.print("Тип джанти (1=STEEL, 2=ALLOY): ");
        int choice = Integer.parseInt(scanner.nextLine());
        return choice == 2 ? Car.RimType.ALLOY : Car.RimType.STEEL;
    }

    // ===== Меню за филтриране =====

    private static void filterByExtrasMenu(Dealership dealership) {
        System.out.println("\n=== Филтър по екстри ===");
        System.out.println("Избери категория:");
        System.out.println("1. AC Type");
        System.out.println("2. Interior");
        System.out.println("3. Rim Type");
        System.out.print("Избор: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> {
                Car.ACType ac = chooseACType();
                dealership.filterByAC(ac);
            }
            case 2 -> {
                Car.Interior interior = chooseInterior();
                dealership.filterByInterior(interior);
            }
            case 3 -> {
                Car.RimType rim = chooseRim();
                dealership.filterByRim(rim);
            }
            default -> System.out.println("Невалиден избор!");
        }
    }
}
