package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Targeta;

import java.util.Collection;

public interface TargetaDao {

    Targeta findById(Targeta targeta);
    Collection<Targeta> findAll();
    void save(Targeta targeta);
    void update(Targeta targeta);
    void delete(Targeta targeta);
    Collection<Targeta> findByIdClient(Client client);
}
