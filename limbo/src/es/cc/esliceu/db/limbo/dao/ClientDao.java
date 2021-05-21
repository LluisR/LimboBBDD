package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.model.Client;

import java.util.Collection;

public interface ClientDao {

    Client findById(Integer id);
    Client findByUserName(String username);
    Client findByEmail(String email);
    Collection<Client> findAll();
    Long save(Client client);
    void update(Client client);
}
