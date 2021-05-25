package es.cc.esliceu.db.limbo.dao.impl;

import es.cc.esliceu.db.limbo.dao.DBConnection;
import es.cc.esliceu.db.limbo.dao.ProvinciaDao;
import es.cc.esliceu.db.limbo.model.Provincia;

import java.util.Collection;

public class ProvinciaDaoImpl implements ProvinciaDao {

    private static ProvinciaDaoImpl instance;
    private DBConnection connection;

    private ProvinciaDaoImpl(DBConnection dbConnection) {
        this.connection = dbConnection;

    }

    @Override
    public Provincia findById(Provincia provincia) {
        return null;
    }

    @Override
    public Collection<Provincia> findAll() {
        return null;
    }

    @Override
    public void save(Provincia provincia) {

    }

    @Override
    public void update(Provincia provincia) {

    }

    public synchronized static ProvinciaDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProvinciaDaoImpl(DBConnectionImpl.getInstance());
        }
        return instance;
    }
}
