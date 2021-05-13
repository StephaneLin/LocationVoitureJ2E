package BLV.DAO;

import BLV.entity.Parking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParkingDAO extends CommonDAO<Parking> {

    ArrayList<Parking> parkingList = new ArrayList<>();
    Parking parking = null;

    public ParkingDAO(Connection conn) {
        super(conn);
    }

    private Parking convertResultSetToParking(ResultSet rs) throws SQLException {
        return new Parking(rs.getInt("parkingId"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getInt("nbrCar"),
                rs.getString("phoneNumber"));
    }

    @Override
    public Parking create(Parking object) {
        try {

            PreparedStatement ps = conn.prepareStatement(ConstantSQL.INSERT_PARKING);
            ps.setString(1, object.getName());
            ps.setString(2, object.getLocation());
            ps.setInt(3, object.getNumOfCar());
            ps.setString(4, object.getTelNumber());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parking;
    }

    @Override
    public boolean delete(Parking object) {
        try {

            PreparedStatement ps = conn.prepareStatement(ConstantSQL.DELETE_PARKING);
            ps.setInt(1, object.getParkingId());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Parking object) {
        try {

            PreparedStatement ps = conn.prepareStatement(ConstantSQL.UPDATE_PARKING);
            ps.setString(1, object.getName());
            ps.setString(2, object.getLocation());
            ps.setInt(3, object.getNumOfCar());
            ps.setString(4, object.getTelNumber());
            ps.setInt(5, object.getParkingId());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Parking findById(int objectId) {
        try {

            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_PARKING_BY_ID);
            ps.setInt(1, objectId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            parking = convertResultSetToParking(rs);

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parking;
    }

    @Override
    public ArrayList<Parking> findAll() {
        try {

            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_ALL_PARKING);

            ResultSet rs = ps.executeQuery();
            rs.next();
            parkingList.add(convertResultSetToParking(rs));

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkingList;
    }

}
