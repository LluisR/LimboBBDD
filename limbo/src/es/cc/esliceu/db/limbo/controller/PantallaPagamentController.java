package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.TargetaDao;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.dao.impl.TargetaDaoImpl;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Targeta;
import es.cc.esliceu.db.limbo.views.PantallaPagamentView;

import java.util.Collection;

public class PantallaPagamentController {

    private final TargetaDao targetaDao;

    public PantallaPagamentController() {
        this.targetaDao = new TargetaDaoImpl(DBConnectionImpl.getInstance());
    }

    public void init(Client client) {
        Collection<Targeta> targetes = this.targetaDao.findByIdClient(client);
        PantallaPagamentView pantallaPagamentView = new PantallaPagamentView();
        client.setTargetes(targetes);
        pantallaPagamentView.init(client);
    }

    public void nextAction(Client client, String option) {
        switch (option) {
            case "0":
            case "1":
            case "2":  PantallaEnviamentController pantallaEnviamentController = new PantallaEnviamentController(); pantallaEnviamentController.init(client, (Targeta)client.getTargetes().toArray()[Integer.parseInt(option)]);
            case "a":  PantallaCreateTargetaController pantallaCreateTargetaController = new PantallaCreateTargetaController(); pantallaCreateTargetaController.init(client, "pagament"); break;
            case "x":  PantallaCistellaController pantallaCistellaController = new PantallaCistellaController(); pantallaCistellaController.init(client); break;
        }
    }
}
