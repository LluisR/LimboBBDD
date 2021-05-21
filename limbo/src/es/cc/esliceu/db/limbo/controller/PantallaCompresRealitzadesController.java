package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.CompraDao;
import es.cc.esliceu.db.limbo.dao.DetallCompraDao;
import es.cc.esliceu.db.limbo.dao.ProducteDao;
import es.cc.esliceu.db.limbo.dao.impl.CompraDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.dao.impl.DetallCompraDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.ProducteDaoImpl;
import es.cc.esliceu.db.limbo.model.*;
import es.cc.esliceu.db.limbo.views.PantallaCompresRealitzadesView;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PantallaCompresRealitzadesController {

    private final CompraDao compraDao;
    private final DetallCompraDao detallCompraDao;
    private final ProducteDao producteDao;

    public PantallaCompresRealitzadesController() {
        this.compraDao = new CompraDaoImpl(DBConnectionImpl.getInstance());
        this.detallCompraDao = new DetallCompraDaoImpl(DBConnectionImpl.getInstance());
        this.producteDao = new ProducteDaoImpl(DBConnectionImpl.getInstance());
    }

    public void init(Client client) {
        PantallaCompresRealitzadesView pantallaCompresRealitzadesView = new PantallaCompresRealitzadesView();
        client.setCompres(this.compraDao.findByIdClient(client));
        client.getCompres().forEach(compra -> {
            Collection<DetallCompra> detallsCompra = this.detallCompraDao.findByIdCompra(compra);
            detallsCompra.forEach(detallCompra -> {
                detallCompra.setProducte(this.producteDao.findById(detallCompra.getProducte().getId()));
            });
            compra.setProductes(detallsCompra);
        });
        pantallaCompresRealitzadesView.init(client);
    }

    public void goBack(Client client) {
        PantallaClientController pantallaClientController = new PantallaClientController();
        pantallaClientController.init(client);
    }
}
