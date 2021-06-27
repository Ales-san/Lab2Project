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
                int id = Integer.parseInt(data[2]);
                char category = data[3].charAt(0);
                Vehicle vehicle;
                switch (category) {
                    case 'A':
                        vehicle = new Motorbike(name, number, id);
                        break;
                    case 'B':
                        vehicle = new Car(name, number, id);
                        break;
                    case 'C':
                        vehicle = new Truck(name, number, id);
                        break;
                    case 'D':
                        vehicle = new Bus(name, number, id);
                        break;
                    case 'T':
                        vehicle = new Tram(name, number, id);
                        break;
                    case 'M':
                        vehicle = new Moped(name, number, id);
                        break;
                    default:
                        throw new DatabaseException("No such category!");
                }
                if(database.containsKey(id)) {
                    database.get(id).add(vehicle);
                } else {
                    ArrayList<Vehicle> vehicles = new ArrayList<>();
                    vehicles.add(vehicle);
                    database.put(id, vehicles);
                }
            } catch (Exception e) {
                throw new DatabaseException("Problems with the format: " + e.getMessage());
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
                    writer.write(Integer.toString(vehicle.getOwnerId()));
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

    public void addVehicle(Person person, Vehicle vehicle) throws DatabaseException {
        if(person.getAge() < vehicle.getRequiredAge()) {
            throw new DatabaseException(person.getName() + " is too young for the " + vehicle.getVehicleType());
        }
        if(person.getVehicleCategory().lastIndexOf(vehicle.getRequiredCategory()) == -1) {
            throw new DatabaseException(person.getName() + " can't be the driver of the " + vehicle.getVehicleType() + " because of the driver license");
        }
        if(person.getId() != vehicle.getOwnerId()) {
            throw new DatabaseException("Vehicle owner id doesn't match with this id: " + person.getId() + ' ' + vehicle.getOwnerId());
        }
        addVehicle(person.getId(), vehicle);
    }

    private void addVehicle(int id, Vehicle vehicle) throws DatabaseException {
        if(database.containsKey(id)) {
            database.get(id).add(vehicle);
        } else {
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            vehicles.add(vehicle);
            database.put(id, vehicles);
        }
    }
}
