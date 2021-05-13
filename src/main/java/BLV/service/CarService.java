package BLV.service;

import BLV.DAO.CarDAO;
import BLV.DAO.ConnectionDataBase;
import BLV.entity.Car;

import java.sql.Connection;
import java.util.List;

public class CarService extends CarDAO {
    private final CarDAO carDAO = new CarDAO(ConnectionDataBase.getInstance().getConnection());

    private CarService(Connection conn) {
        super(conn);
    }

    public static CarService getInstance() {
        return CarService.CarServiceHolder.instance;
    }

    public List<Car> listCars() {
        return carDAO.findAll();
    }

    public Car addCar(String brand, String modele, int carYear, int mileage, int price, byte[] picture, int parking) {
        Car car = new Car(brand, modele, carYear, mileage, price, picture, 0, 0, parking);
        return carDAO.create(car);
    }

    public boolean editCar(String brand, String modele, int carYear, int mileage, int price, byte[] picture, int parking, int carId) {
        Car car = new Car(carId, brand, modele, carYear, mileage, price, picture, 0, 0, parking);
        return carDAO.update(car);
    }

    public Car findCarById(int carId) {
        return carDAO.findById(carId);
    }

    private static class CarServiceHolder {
        private static final CarService instance = new CarService(ConnectionDataBase.getInstance().getConnection());
    }

}
