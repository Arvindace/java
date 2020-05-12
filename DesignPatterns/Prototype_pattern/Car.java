public class Car extends Vehicle {
    Car(){
        this.setType(VehicleType.CAR);
    }
    @Override
    public String toString() {
        return this.type+"--> Name: "+name+" No.ofWheels: "+noOfWheels;
    }
}
