package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.CompraDao;
import es.cc.esliceu.db.limbo.dao.DetallCompraDao;
import es.cc.esliceu.db.limbo.dao.ProducteDao;
import es.cc.esliceu.db.limbo.dao.impl.CompraDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.dao.impl.DetallCompraDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.ProducteDaoImpl;
import es.cc.esliceu.db.limbo.model.Cistella;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Compra;
import es.cc.esliceu.db.limbo.model.DetallCompra;
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
        Map<Cistella, Compra> historialCompres = new HashMap<>();
        Collection<Compra> compres = this.compraDao.findByIdClient(client);
        compres.forEach(compra -> {
            Cistella cistella = new Cistella();
            Collection<DetallCompra> productes = this.detallCompraDao.findByIdCompra(compra);
            productes.forEach(producte -> {
                producte.setProducte(this.producteDao.findById(producte.getProducte().getId()));
            });
            cistella.setProductes(productes);
            /*cistella.calculaTotal();*/
            historialCompres.put(cistella, compra);
        });
        /*client.setCompres(this.compraDao.findByIdClient(client));*/

        pantallaCompresRealitzadesView.init(client, historialCompres);
    }

    public void goBack(Client client) {
        PantallaClientController pantallaClientController = new PantallaClientController();
        pantallaClientController.init(client);
    }
}
