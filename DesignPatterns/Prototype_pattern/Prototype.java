public class Prototype
{
public static void main (String args[]){
    System.out.println("Hello");
    Registry reg = new Registry();

    Car car = (Car) reg.getVehicle(VehicleType.CAR);
    System.out.println("Car "+car);
    car.setName("Maruti");
    System.out.println("Maruti Car "+car);

    Car car2 = (Car) reg.getVehicle(VehicleType.CAR);
    System.out.println("car2 "+car2);

    Bus bus1 = (Bus) reg.getVehicle(VehicleType.BUS);
    bus1.setName("MaxiBus");
    System.out.println("Bus1 = "+bus1);

    bus1.setNumOfSeats(55);
    System.out.println("Bus1 = "+bus1);

    car2.setName("GGG");

    System.out.println("Car1 = "+car);

    Bus bus2 = (Bus) reg.getVehicle(VehicleType.BUS);
    bus2.setName("AVN BUS");
    bus2.setNoOfWheels(8);
    System.out.println(bus2);


}
}
