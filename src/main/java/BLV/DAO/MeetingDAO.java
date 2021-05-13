package BLV.DAO;

import BLV.entity.Meeting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class MeetingDAO extends CommonDAO<Meeting> {

    ArrayList<Meeting> meetingList = new ArrayList<>();
    Meeting meeting = null;

    public MeetingDAO(Connection conn) {
        super(conn);
    }

    @Override
    public Meeting create(Meeting object) {
        try {

            PreparedStatement ps = conn.prepareStatement(ConstantSQL.INSERT_MEETING);
            ps.setDate(1, object.getDate());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meeting;
    }

    @Override
    public boolean delete(Meeting object) {
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.DELETE_MEETING);
            ps.setInt(1, object.getMeetingId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Meeting object) {
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.UPDATE_MEETING);
            ps.setDate(1, object.getDate());
            ps.setInt(2, object.getMeetingId());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Meeting findById(int objectId) {
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_MEETING_BY_ID);
            ps.setInt(1, objectId);

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meeting;
    }

    @Override
    public ArrayList<Meeting> findAll() {
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_ALL_MEETING);

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meetingList;
    }

}
