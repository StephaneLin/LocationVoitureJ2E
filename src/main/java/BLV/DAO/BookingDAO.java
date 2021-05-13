package BLV.DAO;

import BLV.entity.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAO extends CommonDAO<Booking> {

    ArrayList<Booking> bookingList = new ArrayList<>();
    Booking booking = null;

    public BookingDAO(Connection conn) {
        super(conn);
    }

    @Override
    public Booking create(Booking object) {
        try {

            PreparedStatement ps = conn.prepareStatement(ConstantSQL.INSERT_BOOKING);
            ps.setDate(1, object.getFirstDay());
            ps.setDate(2, object.getLastDay());
            ps.setInt(3, object.getStatus());
            ps.setDate(4, object.getBookingDate());
            ps.setInt(5, object.getCarFK());
            ps.setInt(6, object.getMeetingFK());
            ps.setInt(7, object.getUserFK());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    @Override
    public boolean delete(Booking object) {
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.DELETE_BOOKING);
            ps.setInt(1, object.getReservation_id());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Booking object) {
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.UPDATE_BOOKING);
            ps.setDate(1, object.getFirstDay());
            ps.setDate(2, object.getLastDay());
            ps.setInt(3, object.getStatus());
            ps.setDate(4, object.getBookingDate());
            ps.setInt(5, object.getReservation_id());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Booking findById(int objectId) {
        booking = null;
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_BOOKING_BY_ID);
            ps.setInt(1, objectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                booking = convertResultSet(rs);
            }
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }


    @Override
    public ArrayList<Booking> findAll() {
        bookingList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_ALL_BOOKING);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookingList.add(convertResultSet(rs));
            }
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }

    private Booking convertResultSet(ResultSet rs) throws SQLException {
        return new Booking(rs.getInt("reservationId"),
                rs.getDate("debutDate"),
                rs.getDate("finDate"),
                rs.getInt("status"),
                rs.getDate("reservationDate"),
                rs.getInt("vehiculeFK"),
                rs.getInt("rendezvousFK"),
                rs.getInt("utilisateurFK"));
    }

    public ArrayList<Booking> findBookingByUser(int userId) {
        bookingList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_BOOKING_BY_USER);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookingList.add(convertResultSet(rs));
            }
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }
}
