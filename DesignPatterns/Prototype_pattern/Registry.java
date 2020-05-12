import java.util.HashMap;

public class Registry implements Cloneable{
Car car;
Bus bus;
HashMap<VehicleType,Vehicle> vehicles;
Registry(){

    Initialize();
}

public Vehicle getVehicle(VehicleType vt){

    Vehicle veh = null;
    try {
        veh = (Vehicle) vehicles.get(vt).clone();

    }catch (CloneNotSupportedException e){
        e.getMessage();
    }
    return veh;
}

private void Initialize(){
    car = new Car();
    car.setName("Mini");
    car.setNoOfWheels(4);

    bus = new Bus();
    bus.setNumOfSeats(32);
    bus.setNoOfWheels(6);

    vehicles = new HashMap<VehicleType,Vehicle>();
    vehicles.put(VehicleType.BUS,bus);
    vehicles.put(VehicleType.CAR,car);

}
}
