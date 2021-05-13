package BLV.DAO;

import BLV.entity.Car;

import java.sql.*;
import java.util.ArrayList;

public class CarDAO extends CommonDAO<Car> {

    ArrayList<Car> carList;
    Car car;

    public CarDAO(Connection conn) {
        super(conn);
    }

    @Override
    public Car create(Car voiture) {
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.INSERT_CAR);
            ps.setString(1, voiture.getBrand());
            ps.setString(2, voiture.getModele());
            ps.setInt(3, voiture.getCarYear());
            ps.setInt(4, voiture.getMileage());
            ps.setBytes(5, voiture.getPicture());
            ps.setInt(6, voiture.getPrice());
            ps.setInt(7, voiture.getBookingFK());
            ps.setInt(8, voiture.getMaintenanceFK());
            ps.setInt(9, voiture.getParkingFK());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public boolean delete(Car voiture) {
        try {

            PreparedStatement ps = conn.prepareStatement(ConstantSQL.DELETE_CAR);
            ps.setInt(1, voiture.getId());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Car voiture) {
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.UPDATE_CAR);
            ps.setString(1, voiture.getBrand());
            ps.setString(2, voiture.getModele());
            ps.setInt(3, voiture.getCarYear());
            ps.setInt(4, voiture.getMileage());
            ps.setBytes(5, voiture.getPicture());
            ps.setInt(6, voiture.getPrice());
            ps.setInt(7, voiture.getId());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Car findById(int carId) {
        car = null;
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_CAR_BY_ID);
            ps.setInt(1, carId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                car = convertResultSet(rs);
            }
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public ArrayList<Car> findAll() {
        carList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_ALL_CAR);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                carList.add(convertResultSet(rs));
            }
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    private Car convertResultSet(ResultSet rs) throws SQLException {
        return new Car(rs.getInt("vehiculeId"),
                rs.getString("marque"),
                rs.getString("modele"),
                rs.getInt("annee"),
                rs.getInt("kilometrage"),
                rs.getInt("prix"),
                rs.getBytes("photo"),
                rs.getInt("reservationFK"),
                rs.getInt("maintenanceFK"),
                rs.getInt("parkingFK"));
    }

    public ArrayList<Car> findAvailableCar(Date start, Date end, int park) {
        carList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_AVAILABLE_CAR);
            ps.setInt(1, park);
            ps.setDate(2, end);
            ps.setDate(3, start);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                carList.add(convertResultSet(rs));
            }
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

}
