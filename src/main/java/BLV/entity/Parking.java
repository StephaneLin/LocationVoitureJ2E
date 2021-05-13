package BLV.entity;

public class Parking {

    private int parkingId;
    private String name;
    private String location;
    private int numOfCar;
    private String telNumber;

    public Parking(int parkingId, String name, String location, int numOfCar, String telNumber) {
        this.parkingId = parkingId;
        this.name = name;
        this.location = location;
        this.numOfCar = numOfCar;
        this.telNumber = telNumber;
    }

    public Parking(String nom, String adresse, int nbrV, String num) {
        this.name = nom;
        this.location = adresse;
        this.numOfCar = nbrV;
        this.telNumber = num;
    }

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public int getNumOfCar() {
        return numOfCar;
    }

    public void setNumOfCar(int numOfCar) {
        this.numOfCar = numOfCar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @Override
    public String toString() {
        return "Parking{" +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", numOfCar=" + numOfCar +
                ", telNumber='" + telNumber + '\'' +
                '}';
    }
}
