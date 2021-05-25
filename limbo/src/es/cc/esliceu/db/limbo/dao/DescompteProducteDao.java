package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.model.Descompte;
import es.cc.esliceu.db.limbo.model.DescompteProducte;

import java.util.Collection;

public interface DescompteProducteDao {

    DescompteProducte findById(DescompteProducte descompteProducte);
    Collection<DescompteProducte> findAll();
    void save(DescompteProducte descompteProducte);
    void update(DescompteProducte descompteProducte);
    void delete(DescompteProducte descompteProducte);
    Collection<DescompteProducte> findAllByIdDescompte(Descompte descompte);
}
