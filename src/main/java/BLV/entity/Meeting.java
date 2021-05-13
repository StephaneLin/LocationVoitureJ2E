package BLV.entity;

import java.sql.Date;

public class Meeting {

    private int meetingId;
    private Date date;

    public Meeting(int meetingId, Date date) {
        this.meetingId = meetingId;
        this.date = date;
    }

    public Meeting(Date date) {
        this.date = date;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
