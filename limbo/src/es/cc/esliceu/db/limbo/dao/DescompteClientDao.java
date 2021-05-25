package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.DescompteClient;

import java.util.Collection;

public interface DescompteClientDao {

    DescompteClient findById(DescompteClient descompteClient);
    Collection<DescompteClient> findAll();
    void save(DescompteClient descompteClient);
    void update(DescompteClient descompteClient);
    void delete(DescompteClient descompteClient);
    Collection<DescompteClient> findAllByIdClient(Client client);
}
