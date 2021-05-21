package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.model.Categoria;

import java.util.Collection;

public interface CategoriaDao {

    Categoria findById(Categoria categoria);
    Collection<Categoria> findAll();
    void save(Categoria categoria);
    void update(Categoria categoria);
}
