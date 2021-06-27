import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void printVehicle(Vehicle v) {
        System.out.println("type: " + v.getVehicleType() + " | name: " + v.getName() + " | number: "
                + v.getNumber() + " | owner: " + v.getOwnerId() + " | age: "
                + v.getRequiredAge() + " | category: " + v.getRequiredCategory());
    }

    public static void main(String[] args) {
        try {
            Vehicle moped = new Moped("GTI", "K842YE", 123);
            Vehicle car = new Car("JHBIML", "K578PO", 123);
            //printVehicle(tram);
            Person john = new Person("John Lee", 123, 18, "MB");
            DriverDatabase database = new DriverDatabase();
            database.addVehicle(john, moped);
            database.addVehicle(john, car);
            for(Vehicle v : database.getPersonVehicles(123)) {
                printVehicle(v);
            }

            Random rand = new Random();

            for(int i = 0; i < 10000000; i++) {
                char randChar = (char)('a' + rand.nextInt(26));
                char randChar1 = (char)('a' + rand.nextInt(26));

                int id = i + rand.nextInt(100);
                String number = "ETYOPAHKXCBM".charAt(rand.nextInt(12))
                        + String.valueOf((char) (rand.nextInt(10) + '0'))
                        + String.valueOf((char) (rand.nextInt(10) + '0'))
                        + String.valueOf((char) (rand.nextInt(10) + '0'))
                        + "ETYOPAHKXCBM".charAt(rand.nextInt(12))
                        + "ETYOPAHKXCBM".charAt(rand.nextInt(12));

                if(randChar < 'n') {
                    database.addVehicle(new Person("Jack Walk" + randChar, id, 21 + rand.nextInt(30), "BT"),
                            new Car(String.valueOf(randChar) + randChar1 + randChar + randChar, number, id));
                } else {
                    database.addVehicle(new Person("James Smi" + randChar, id, 21 + rand.nextInt(30), "BT"),
                            new Tram(String.valueOf(randChar) + randChar + randChar1 + randChar1, number, id));
                }
            }

            try (FileWriter writer = new FileWriter("dump.txt")) {
                database.dump(writer);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            try (Scanner scanner = new Scanner(new File("dump.txt"))) {
                DriverDatabase database1 = new DriverDatabase(scanner);
                for(Vehicle v : database.getPersonVehicles(rand.nextInt(100000))) {
                    printVehicle(v);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } catch (VehicleException | DatabaseException | PersonException e) {
            System.out.println(e.getMessage());
        }
    }


}
