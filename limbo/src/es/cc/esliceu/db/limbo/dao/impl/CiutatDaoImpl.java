package es.cc.esliceu.db.limbo.dao.impl;

import es.cc.esliceu.db.limbo.dao.CiutatDao;
import es.cc.esliceu.db.limbo.dao.DBConnection;
import es.cc.esliceu.db.limbo.model.Ciutat;

import java.util.Collection;

public class CiutatDaoImpl implements CiutatDao {

    private static CiutatDaoImpl instance;
    private DBConnection connection;

    private CiutatDaoImpl(DBConnection dbConnection) {
        this.connection = dbConnection;
    }


    @Override
    public Ciutat findById(Ciutat ciutat) {
        return null;
    }

    @Override
    public Collection<Ciutat> findAll() {
        return null;
    }

    @Override
    public void save(Ciutat ciutat) {

    }

    @Override
    public void update(Ciutat ciutat) {

    }

    public synchronized static CiutatDaoImpl getInstance() {
        if (instance == null) {
            instance = new CiutatDaoImpl(DBConnectionImpl.getInstance());
        }
        return instance;
    }
}
