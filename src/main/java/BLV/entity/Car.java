package BLV.entity;

public class Car {

    private int carId;
    private String brand;
    private String modele;
    private int carYear;
    private int mileage;
    private int price;
    private byte[] picture;
    private int bookingFK;
    private int maintenanceFK;
    private int parkingFK;

    public Car(int id, String marque, String modele, int annee, int kilometrage, int prix, byte[] photo, int bookingFK, int maintenance, int parking) {
        this.carId = id;
        this.brand = marque;
        this.modele = modele;
        this.carYear = annee;
        this.mileage = kilometrage;
        this.price = prix;
        this.picture = photo;
        this.bookingFK = bookingFK;
        this.maintenanceFK = maintenance;
        this.parkingFK = parking;
    }

    public Car(String brand, String modele, int carYear, int mileage, int price, byte[] picture, int bookingFK, int maintenanceFK, int parking) {
        this.brand = brand;
        this.modele = modele;
        this.carYear = carYear;
        this.mileage = mileage;
        this.price = price;
        this.picture = picture;
        this.bookingFK = bookingFK;
        this.maintenanceFK = maintenanceFK;
        this.parkingFK = parking;
    }

    public int getId() {
        return carId;
    }

    public void setCarId(int id) {
        this.carId = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public int getBookingFK() {
        return bookingFK;
    }

    public void setBookingFK(int bookingFK) {
        this.bookingFK = bookingFK;
    }

    public int getMaintenanceFK() {
        return maintenanceFK;
    }

    public void setMaintenanceFK(int maintenanceFK) {
        this.maintenanceFK = maintenanceFK;
    }

    public int getParkingFK() {
        return parkingFK;
    }

    public void setParkingFK(int parkingFK) {
        this.parkingFK = parkingFK;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", modele='" + modele + '\'' +
                ", carYear=" + carYear +
                ", mileage=" + mileage +
                ", price=" + price +
                '}';
    }
}
