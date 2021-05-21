package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.ProducteDao;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.dao.impl.ProducteDaoImpl;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.views.PantallaAjudaView;

public class PantallaAjudaController {

    private final ProducteDao producteDao;

    public PantallaAjudaController() {
        this.producteDao = new ProducteDaoImpl(DBConnectionImpl.getInstance());
    }

    public void init(Client client) {
        PantallaAjudaView pantallaAjudaView = new PantallaAjudaView();
        pantallaAjudaView.init(client);
    }

    public void init() {
        PantallaAjudaView pantallaAjudaView = new PantallaAjudaView();
        pantallaAjudaView.init(null);
    }

    public void goBack(Client client) {
        if (client == null) {
            PantallaInicialController pantallaInicialController = new PantallaInicialController();
            pantallaInicialController.init();
        } else {
            PantallaPrincipalController pantallaPrincipalController = new PantallaPrincipalController();
            pantallaPrincipalController.init(client);
        }
    }
}