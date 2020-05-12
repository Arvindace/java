public class Bus extends Vehicle{

    Bus(){
        this.setType(VehicleType.BUS);
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    int numOfSeats;

    @Override
    public String toString() {
        return this.type+"--> Name: "+name+" No.ofWheels: "+noOfWheels+ "Num of seats = "+numOfSeats;
    }
}
