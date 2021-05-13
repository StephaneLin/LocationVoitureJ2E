package BLV.DAO;

import BLV.entity.Maintenance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaintenanceDAO extends CommonDAO<Maintenance> {

    ArrayList<Maintenance> maintenanceList = new ArrayList<>();
    Maintenance maintenance = null;

    public MaintenanceDAO(Connection conn) {
        super(conn);
    }

    @Override
    public Maintenance create(Maintenance object) {
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.INSERT_MAINTENANCE);
            ps.setBoolean(1, object.isPneu());
            ps.setBoolean(2, object.isCarosserie());
            ps.setBoolean(3, object.isElectronique());
            ps.setBoolean(4, object.isMoteur());
            ps.setBoolean(5, object.isConforme());
            ps.setInt(6, object.getVehiculeFK());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maintenance;
    }

    @Override
    public boolean delete(Maintenance object) {
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.DELETE_MAINTENANCE);
            ps.setInt(1, object.getMaintenanceId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Maintenance object) {
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.UPDATE_MAINTENANCE);
            ps.setBoolean(1, object.isPneu());
            ps.setBoolean(2, object.isCarosserie());
            ps.setBoolean(3, object.isElectronique());
            ps.setBoolean(4, object.isMoteur());
            ps.setBoolean(4, object.isConforme());
            ps.setInt(6, object.getMaintenanceId());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Maintenance findById(int objectId) {
        maintenance = null;
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_MAINTENANCE_BY_ID);
            ps.setInt(1, objectId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            maintenance = convertResultSet(rs);

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maintenance;
    }

    @Override
    public ArrayList<Maintenance> findAll() {
        maintenanceList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(ConstantSQL.FIND_ALL_MAINTENANCE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maintenanceList.add(convertResultSet(rs));
            }
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maintenanceList;
    }

    private Maintenance convertResultSet(ResultSet rs) throws SQLException {
        return new Maintenance(rs.getInt("maintenanceId"),
                rs.getBoolean("pneu"),
                rs.getBoolean("carosserie"),
                rs.getBoolean("electronique"),
                rs.getBoolean("moteur"),
                rs.getBoolean("conforme"),
                rs.getInt("vehiculeFK"));
    }
}
