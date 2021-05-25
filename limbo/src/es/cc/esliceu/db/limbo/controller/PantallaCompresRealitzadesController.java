package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.CompraDao;
import es.cc.esliceu.db.limbo.dao.DetallCompraDao;
import es.cc.esliceu.db.limbo.dao.ProducteDao;
import es.cc.esliceu.db.limbo.dao.impl.CompraDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.DetallCompraDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.ProducteDaoImpl;
import es.cc.esliceu.db.limbo.model.*;
import es.cc.esliceu.db.limbo.views.PantallaCompresRealitzadesView;

import java.util.Collection;

public class PantallaCompresRealitzadesController {

    private final CompraDao compraDao;
    private final DetallCompraDao detallCompraDao;
    private final ProducteDao producteDao;

    public PantallaCompresRealitzadesController() {
        this.compraDao = CompraDaoImpl.getInstance();
        this.detallCompraDao = DetallCompraDaoImpl.getInstance();
        this.producteDao = ProducteDaoImpl.getInstance();
    }

    public void init(Client client) {
        client.setCompres(this.compraDao.findByIdClient(client));
        client.getCompres().forEach(compra -> {
            Collection<DetallCompra> detallsCompra = this.detallCompraDao.findByIdCompra(compra);
            detallsCompra.forEach(detallCompra -> {
                detallCompra.setProducte(this.producteDao.findById(detallCompra.getProducte().getId()));
            });
            compra.setProductes(detallsCompra);
        });
        PantallaCompresRealitzadesView.getInstance().init(client);
    }

    public void goBack(Client client) {
        PantallaClientController pantallaClientController = new PantallaClientController();
        pantallaClientController.init(client);
    }
}
