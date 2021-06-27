import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Person {
    private String name;
    private int id;
    private int age;
    private String vehicleCategories;

    public Person(String name, int id, int age, String vehicleCategories) throws PersonException {
        if(name.matches("\\D*-\\D*")) {
            throw new PersonException("The name can't contain '-' or numbers: " + name);
        }
        if(id < 0) {
            throw new PersonException("ID can't be less than zero");
        }
        if(age <= 0) {
            throw new PersonException("Age can't be less or equal than zero");
        }
        for(char category : vehicleCategories.toCharArray()) {
            if ("ABCDMT".lastIndexOf(category) == -1) {
                throw new PersonException("There is no such vehicle category");
            }
        }
        this.name = name;
        this.id = id;
        this.age = age;
        this.vehicleCategories = vehicleCategories;
    }

    public static Person read(Scanner scanner) throws PersonException {
        String data[] = scanner.nextLine().split("-");
        if(data.length < 4) {
            throw new PersonException("There wrong format of the input: " + data);
        }
        try {
            Person person = new Person(data[0], Integer.getInteger(data[1]), Integer.getInteger(data[2]), data[3]);
            return person;
        } catch (Exception e) {
            throw new PersonException("Problems with the format" + e.getMessage());
        }
    }

    public void write(FileWriter writer) throws IOException {
        writer.write(name);
        writer.write('-');
        writer.write(id);
        writer.write('-');
        writer.write(age);
        writer.write('-');
        writer.write(vehicleCategories);
        writer.write('\n');
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getVehicleCategory() {
        return vehicleCategories;
    }

    public void updateAge() {
        age++;
    }
}
