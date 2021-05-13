package BLV.service;

import BLV.DAO.ConnectionDataBase;
import BLV.DAO.MaintenanceDAO;
import BLV.entity.Maintenance;

import java.sql.Connection;
import java.util.List;

public class MaintenanceService extends MaintenanceDAO {
    private final MaintenanceDAO maintenanceDAO = new MaintenanceDAO(ConnectionDataBase.getInstance().getConnection());

    private MaintenanceService(Connection conn) {
        super(conn);
    }

    public static MaintenanceService getInstance() {
        return MaintenanceService.MaintenanceServiceHolder.instance;
    }

    public List<Maintenance> listMaintenances() {
        return maintenanceDAO.findAll();
    }

    public Maintenance addMaintenance(boolean tire, boolean body, boolean electronic, boolean motor, boolean conform, int vehiculeFK) {
        Maintenance maintenance = new Maintenance(tire, body, electronic, motor, conform, vehiculeFK);
        return maintenanceDAO.create(maintenance);
    }

    private static class MaintenanceServiceHolder {
        private static final MaintenanceService instance = new MaintenanceService(ConnectionDataBase.getInstance().getConnection());
    }
}
