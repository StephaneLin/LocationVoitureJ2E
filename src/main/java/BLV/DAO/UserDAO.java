package BLV.DAO;

import BLV.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO extends CommonDAO<User> {

    static final Logger LOGGER = LogManager.getLogger();
    User user = null;
    ArrayList<User> userList = new ArrayList<>();

    public UserDAO(Connection conn) {
        super(conn);
    }

    @Override
    public User create(User object) {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        if (object.getEntitee() == 0) {
            try {
                PreparedStatement ps = conn.prepareStatement(ConstantSQL.INSERT_USER_CLIENT);
                ps.setDate(1, date);
                ps.setString(2, object.getUserLastName());
                ps.setString(3, object.getUserFirstName());
                ps.setInt(4, object.getUserPhone());
                ps.setString(5, object.getEmail());
                ps.setInt(6, object.getUserYearOld());
                ps.setString(7, object.getPassword());
                ps.setInt(8, object.getAccessRightsFK());
                ps.setInt(9, object.getPaymentCardFK());
                ps.setBytes(10, object.getLicenceFK());

                ps.execute();
                ps.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement ps = conn.prepareStatement(ConstantSQL.INSERT_USER_ENTERPRISE);
                ps.setDate(1, date);
                ps.setString(2, object.getCompanyName());
                ps.setInt(3, object.getCompanyPhone());
                ps.setInt(4, object.getSiret());
                ps.setString(5, object.getEmail());
                ps.setString(6, object.getPassword());

                ps.execute();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public boolean delete(User object) {
        try {

            PreparedStatement ps = conn.prepareStatement(ConstantSQL.DELETE_USER);
            ps.setInt(1, object.getUserId());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(User object) {

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        if (object.getEntitee() == 0) {
            try {
                PreparedStatement ps = conn.prepareStatement(ConstantSQL.UPDATE_USER_CLIENT);

                ps.setDate(1, date);
                ps.setInt(2, object.getUserPhone());
                ps.setInt(3, object.getUserYearOld());
                ps.setString(4, object.getEmail());
                ps.setString(5, object.getPassword());
                ps.setInt(6, object.getUserId());

                ps.execute();
                ps.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement ps = conn.prepareStatement(ConstantSQL.UPDATE_USER_ENTREPRISE);

                ps.setDate(1, date);
                ps.setInt(2, object.getCompanyPhone());
                ps.setString(3, object.getPassword());
                ps.setString(4, object.getEmail());
                ps.setInt(5, object.getUserId());

                ps.execute();
                ps.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public User findById(int objectId) {
        user = null;
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_USER_BY_ID);
            ps.setInt(1, objectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = convertResultSet(rs);
            }
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public ArrayList<User> findAll() {
        userList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(ConstantSQL.FIND_ALL_USER);
            while (rs.next()) {
                userList.add(convertResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.debug("Probleme lors de la formation de la liste des users");
        }
        return userList;
    }

    private User convertResultSet(ResultSet rs) throws SQLException {
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

    private User convertResultSetHidePassword(ResultSet rs) throws SQLException {
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
                rs.getInt("accessRightsFK"),
                rs.getInt("paymentCardFK"),
                rs.getBytes("licenceFK"));
    }

    public User findUserByEmail(String email) {
        user = null;
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_USER_BY_EMAIL);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = convertResultSet(rs);
            }
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public ArrayList<User> findUsersForManagement() throws SQLException {
        userList = new ArrayList<>();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(ConstantSQL.FIND_USER_ENTREPRISE);
        while (rs.next()) {
            userList.add(convertResultSetHidePassword(rs));
        }
        rs = s.executeQuery(ConstantSQL.FIND_USER_CLIENT);
        while (rs.next()) {
            userList.add(convertResultSetHidePassword(rs));
        }
        s.close();
        return userList;
    }

}


