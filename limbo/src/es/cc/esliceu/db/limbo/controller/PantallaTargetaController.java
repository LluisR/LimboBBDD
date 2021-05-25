package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.TargetaDao;
import es.cc.esliceu.db.limbo.dao.impl.TargetaDaoImpl;
import es.cc.esliceu.db.limbo.model.Client;

public class PantallaTargetaController {

    private final TargetaDao targetaDao;

    public PantallaTargetaController() {
        this.targetaDao = TargetaDaoImpl.getInstance();
    }

    public void init(Client client) {
    }
}
