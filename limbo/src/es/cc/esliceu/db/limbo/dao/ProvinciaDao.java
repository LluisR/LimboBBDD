package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.model.Provincia;

import java.util.Collection;

public interface ProvinciaDao {

    Provincia findById(Provincia provincia);
    Collection<Provincia> findAll();
    void save(Provincia provincia);
    void update(Provincia provincia);
}
