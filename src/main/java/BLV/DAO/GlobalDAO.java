package BLV.DAO;

import BLV.entity.Car;
import BLV.entity.Parking;
import BLV.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GlobalDAO {

    protected Connection conn = null;
    ArrayList<Object> objectList = new ArrayList<>();

    public GlobalDAO(Connection con) {
        this.conn = con;
    }

    private Car convertResultSetToCar(ResultSet rs) throws SQLException {
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

    public List<Car> findCar(String mot) {
        List<Car> carList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.Global_Search_Car);
            ps.setString(1, "%" + mot + "%");
            ps.setString(2, "%" + mot + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                carList.add(convertResultSetToCar(rs));
            }
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    private Parking convertResultSetToParking(ResultSet rs) throws SQLException {
        return new Parking(rs.getInt("parkingId"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getInt("nbrCar"),
                rs.getString("phoneNumber"));
    }

    public List<Parking> findParking(String mot) {
        List<Parking> parkingList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.Global_Search_Parking);
            ps.setString(1, "%" + mot + "%");
            ps.setString(2, "%" + mot + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                parkingList.add(convertResultSetToParking(rs));
            }
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkingList;
    }

    private User convertResultSetToUser(ResultSet rs) throws SQLException {
        return new User(rs.getInt("userId"),
                rs.getInt("entitee"),
                rs.getDate("connexionDate"),
                rs.getString("companyName"),
                rs.getInt("companyPhone"),
                rs.getInt("siret"),
                rs.getString("userLastName"),
                rs.getString("userFirstName"),
                rs.getInt("userPhone"),
                rs.getInt("userYearOld"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("accessRightsFK"),
                rs.getInt("paymentCardFK"),
                rs.getBytes("licenceFK"));
    }

    public List<User> findUser(String mot) {
        List<User> userList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.Global_Search_User);
            ps.setString(1, "%" + mot + "%");
            ps.setString(2, "%" + mot + "%");
            ps.setString(3, "%" + mot + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(convertResultSetToUser(rs));
            }
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<Object> search(String mot) {
        findCar(mot);
        findParking(mot);
        findUser(mot);
        return objectList;
    }
}
