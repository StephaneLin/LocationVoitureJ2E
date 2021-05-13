package BLV.DAO;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class CommonDAO<T> {

    protected Connection conn = null;

    public CommonDAO(Connection conn) {
        this.conn = conn;
    }

    public abstract T create(T object);

    public abstract boolean delete(T object);

    public abstract boolean update(T object);

    public abstract T findById(int objectId);

    public abstract ArrayList<T> findAll();

}
