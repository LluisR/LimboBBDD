package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.model.Producte;

import java.util.Collection;
import java.util.Map;

public interface ProducteDao {

    Producte findById(Integer id);
    Collection<Producte> findAll();
    void save(Producte producte);
    void update(Producte producte);
    Collection<Producte> findSuggested();
    Collection<Producte> findWithFilters(Map<String,Integer> mapaFiltres, String sql, String nom, String descripcio, String marca, String categoria);
}
