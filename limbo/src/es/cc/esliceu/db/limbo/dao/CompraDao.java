package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Compra;

import java.util.Collection;

public interface CompraDao {

    Compra findById(Compra compra);
    Collection<Compra> findAll();
    Integer save(Compra compra);
    void update(Compra compra);
    Collection<Compra> findByIdClient(Client client);
}
