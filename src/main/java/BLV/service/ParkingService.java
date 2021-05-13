package BLV.service;

import BLV.DAO.CarDAO;
import BLV.DAO.ConnectionDataBase;
import BLV.DAO.ParkingDAO;
import BLV.entity.Car;
import BLV.entity.Parking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ParkingService extends ParkingDAO {
    static final Logger LOGGER = LogManager.getLogger();
    private final ParkingDAO parkingDao = new ParkingDAO(ConnectionDataBase.getInstance().getConnection());
    private final CarDAO carDAO = new CarDAO(ConnectionDataBase.getInstance().getConnection());

    private ParkingService(Connection conn) {
        super(conn);
    }

    public static ParkingService getInstance() {
        return ParkingServiceHolder.instance;
    }

    public List<Parking> listParkings() {
        return parkingDao.findAll();
    }

    public List<Car> listCarsFromParking(String begDate, String endDate, int parkingId) {
        List<Car> carList = new ArrayList<>();

        Date begginingDate = Date.valueOf(begDate);
        Date endingDate = Date.valueOf(endDate);

        carList = carDAO.findAvailableCar(begginingDate, endingDate, parkingId);

        return carList;
    }

    private static class ParkingServiceHolder {
        private static final ParkingService instance = new ParkingService(ConnectionDataBase.getInstance().getConnection());
    }
}
