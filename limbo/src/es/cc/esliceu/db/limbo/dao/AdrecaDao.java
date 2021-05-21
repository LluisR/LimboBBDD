package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.model.Adreca;
import es.cc.esliceu.db.limbo.model.Client;

import java.util.Collection;

public interface AdrecaDao {

    Adreca findById(Adreca adreca);
    Collection<Adreca> findAll();
    void save(Adreca adreca);
    void update(Adreca adreca);
    void delete(Adreca adreca);
    Collection<Adreca> findAllByIdClient(Client client);
}
