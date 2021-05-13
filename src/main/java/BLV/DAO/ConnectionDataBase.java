package BLV.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDataBase {

    private static ConnectionDataBase instance;
    private static Connection conn;

    private ConnectionDataBase() {
    }

    public static ConnectionDataBase getInstance() {
        if (instance == null) {
            instance = new ConnectionDataBase();
        }
        return instance;
    }

    public Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            try {
                conn = DataSourceProvider.getDataSource().getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Connexion erreur");
            }
        }
        return conn;
    }
}
