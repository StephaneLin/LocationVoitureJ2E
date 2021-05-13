package BLV.service;

import BLV.DAO.ConnectionDataBase;
import BLV.DAO.GlobalDAO;
import BLV.entity.Car;
import BLV.entity.Parking;
import BLV.entity.User;

import java.sql.Connection;
import java.util.List;

public class SearchService extends GlobalDAO {

    Connection con = ConnectionDataBase.getInstance().getConnection();
    private final GlobalDAO globalDAO = new GlobalDAO(con);

    public SearchService(Connection conn) {
        super(conn);
    }

    public static SearchService getInstance() {
        return SearchService.SearchServiceHolder.instance;
    }

    public List<Car> searchCar(String mot) {
        return globalDAO.findCar(mot);
    }

    public List<Parking> searchParking(String mot) {
        return globalDAO.findParking(mot);
    }

    public List<User> searchUser(String mot) {
        return globalDAO.findUser(mot);
    }

    private static class SearchServiceHolder {
        private static final SearchService instance = new SearchService(ConnectionDataBase.getInstance().getConnection());
    }

}
