package BLV.service;

import BLV.DAO.BookingDAO;
import BLV.DAO.ConnectionDataBase;
import BLV.entity.Booking;
import BLV.entity.User;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class BookingService extends BookingDAO {
    private final BookingDAO bookingDAO = new BookingDAO(ConnectionDataBase.getInstance().getConnection());

    private BookingService(Connection conn) {
        super(conn);
    }

    public static BookingService getInstance() {
        return BookingService.BookingServiceHolder.instance;
    }

    public List<Booking> listBookings() {
        return bookingDAO.findAll();
    }

    public List<Booking> listBookingsFromUserId(User user) {
        return bookingDAO.findBookingByUser(user.getUserId());
    }

    public Booking addBooking(Date firstDay, Date lastDay, int status, Date bookingDate, int carFK, int meetingFK, int userFK) {
        Booking booking = new Booking(firstDay, lastDay, status, bookingDate, carFK, meetingFK, userFK);
        return bookingDAO.create(booking);
    }

    private static class BookingServiceHolder {
        private static final BookingService instance = new BookingService(ConnectionDataBase.getInstance().getConnection());
    }
}
