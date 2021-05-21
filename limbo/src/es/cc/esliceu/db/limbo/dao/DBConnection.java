package es.cc.esliceu.db.limbo.dao;

import java.sql.Connection;

public interface DBConnection {

    Connection getConnection();

    void disconnect();

}
