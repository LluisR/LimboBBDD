package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.model.Ciutat;

import java.util.Collection;

public interface CiutatDao {

    Ciutat findById(Ciutat ciutat);
    Collection<Ciutat> findAll();
    void save(Ciutat ciutat);
    void update(Ciutat ciutat);
}
