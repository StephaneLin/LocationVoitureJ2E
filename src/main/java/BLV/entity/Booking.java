package BLV.entity;

import java.sql.Date;

public class Booking {

    private int reservation_id;
    private Date firstDay;
    private Date lastDay;
    private int status;
    private Date bookingDate;
    private int carFK;
    private int meetingFK;
    private int userFK;

    public Booking(int reservation_id, Date debut, Date fin, int status, Date dateReservation, int car, int meeting, int user) {
        this.reservation_id = reservation_id;
        this.firstDay = debut;
        this.lastDay = fin;
        this.status = status;
        this.bookingDate = dateReservation;
        this.carFK = car;
        this.meetingFK = meeting;
        this.userFK = user;
    }

    public Booking(Date firstDay, Date lastDay, int status, Date bookingDate, int carFK, int meetingFK, int userFK) {
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.status = status;
        this.bookingDate = bookingDate;
        this.carFK = carFK;
        this.meetingFK = meetingFK;
        this.userFK = userFK;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Date getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }

    public Date getLastDay() {
        return lastDay;
    }

    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getCarFK() {
        return carFK;
    }

    public void setCarFK(int carFK) {
        this.carFK = carFK;
    }

    public int getMeetingFK() {
        return meetingFK;
    }

    public void setMeetingFK(int meetingFK) {
        this.meetingFK = meetingFK;
    }

    public int getUserFK() {
        return userFK;
    }

    public void setUserFK(int userFK) {
        this.userFK = userFK;
    }

}
