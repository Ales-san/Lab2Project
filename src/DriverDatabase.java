import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DriverDatabase {
    private Map<Integer, ArrayList<Vehicle>> database;

    public DriverDatabase(Scanner scanner) throws DatabaseException {
        database = new HashMap<>();
        while (scanner.hasNextLine()) {
            String data[] = scanner.nextLine().split("-");
            if(data.length < 4 || data[3].length() != 1 || !Vehicle.checkNumber(data[1])) {
                throw new DatabaseException("There wrong format of the input: " + data);
            }
            try {
                String name = data[0];
                String number = data[1];
                int id = Integer.getInteger(data[2]);
                char category = data[3].charAt(0);
                Vehicle vehicle = switch (category) {
                    case 'A' -> new Motorbike(name, number, id);
                    case 'B' -> new Car(name, number, id);
                    case 'C' -> new Truck(name, number, id);
                    case 'D' -> new Bus(name, number, id);
                    case 'T' -> new Tram(name, number, id);
                    case 'M' -> new Moped(name, number, id);
                    default -> throw new DatabaseException("No such category!");
                };
                if(database.containsKey(id)) {
                    database.get(id).add(vehicle);
                } else {
                    ArrayList<Vehicle> vehicles = new ArrayList<>();
                    vehicles.add(vehicle);
                    database.put(id, vehicles);
                }
            } catch (Exception e) {
                throw new DatabaseException("Problems with the format" + e.getMessage());
            }
        }
    }

    public DriverDatabase() {
        database = new HashMap<>();
    }

    public void dump(FileWriter writer) throws DatabaseException {
        try {
            for (ArrayList<Vehicle> vehicles : database.values()) {
                for (Vehicle vehicle : vehicles) {
                    writer.write(vehicle.getName());
                    writer.write("-");
                    writer.write(vehicle.getNumber());
                    writer.write("-");
                    writer.write(vehicle.getOwnerId());
                    writer.write("-");
                    writer.write(vehicle.getRequiredCategory());
                    writer.write('\n');
                }
            }
        } catch (IOException e) {
            throw new DatabaseException("Problems with the dump " + e.getMessage());
        }
    }

    public ArrayList<Vehicle> getPersonVehicles(Person person) {
        return getPersonVehicles(person.getId());
    }

    public ArrayList<Vehicle> getPersonVehicles(int id) {
        return (ArrayList<Vehicle>) database.getOrDefault(id, new ArrayList<>()).clone();
    }

    public void addVehicle(int id, Vehicle vehicle) throws DatabaseException {
        if(vehicle.getOwnerId() != id) {
            throw new DatabaseException("Vehicle owner id doesn't match with this id: " + id + ' ' + vehicle.getOwnerId());
        }
        if(database.containsKey(id)) {
            database.get(id).add(vehicle);
        } else {
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            vehicles.add(vehicle);
            database.put(id, vehicles);
        }
    }
}
