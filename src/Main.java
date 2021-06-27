public class Main {
    public static void main(String[] args) {
        try {
            Vehicle v = new Tram("GTI", "K842YE", 123);
            System.out.println(v.getVehicleType() + " " + v.getName() + " "
                    + v.getNumber() + " " + v.getOwnerId() + " "
                    + v.getRequiredAge() + " " + v.getRequiredCategory());
        } catch (VehicleException e) {
            System.out.println();
        }

    }
}
