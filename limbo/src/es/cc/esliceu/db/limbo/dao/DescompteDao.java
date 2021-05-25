package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Descompte;

import java.util.Collection;

public interface DescompteDao {

    Descompte findById(Descompte descompte);
    Collection<Descompte> findAll();
    void save(Descompte descompte);
    void update(Descompte descompte);
    void delete(Descompte descompte);
    Collection<Descompte> findAllByIdClient(Client client);
}
