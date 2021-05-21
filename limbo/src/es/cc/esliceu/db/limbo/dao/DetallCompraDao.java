package es.cc.esliceu.db.limbo.dao;

import es.cc.esliceu.db.limbo.model.Compra;
import es.cc.esliceu.db.limbo.model.DetallCompra;

import java.util.Collection;

public interface DetallCompraDao {

    DetallCompra findById(DetallCompra detallCompra);
    Collection<DetallCompra> findAll();
    void save(DetallCompra detallCompra);
    void update(DetallCompra detallCompra);
    Collection<DetallCompra> findByIdCompra(Compra compra);
}
