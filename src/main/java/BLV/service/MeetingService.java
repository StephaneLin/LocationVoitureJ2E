package BLV.service;

import BLV.DAO.ConnectionDataBase;
import BLV.DAO.MeetingDAO;
import BLV.entity.Meeting;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class MeetingService extends MeetingDAO {
    private final MeetingDAO meetingDAO = new MeetingDAO(ConnectionDataBase.getInstance().getConnection());

    private MeetingService(Connection conn) {
        super(conn);
    }

    public static MeetingService getInstance() {
        return MeetingService.MeetingServiceHolder.instance;
    }

    public List<Meeting> listMeetings() {
        return meetingDAO.findAll();
    }

    public Meeting addMeeting(Date date) {
        Meeting meeting = new Meeting(date);
        return meetingDAO.create(meeting);
    }

    private static class MeetingServiceHolder {
        private static final MeetingService instance = new MeetingService(ConnectionDataBase.getInstance().getConnection());
    }
}
