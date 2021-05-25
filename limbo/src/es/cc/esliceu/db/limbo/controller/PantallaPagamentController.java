package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.CompraDao;
import es.cc.esliceu.db.limbo.dao.TargetaDao;
import es.cc.esliceu.db.limbo.dao.impl.CompraDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.TargetaDaoImpl;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Targeta;
import es.cc.esliceu.db.limbo.views.PantallaPagamentView;

import java.util.Collection;

public class PantallaPagamentController {

    private static PantallaPagamentController instance;
    private final TargetaDao targetaDao;
    private final CompraDao compraDao;

    private PantallaPagamentController() {
        this.targetaDao = TargetaDaoImpl.getInstance();
        this.compraDao = CompraDaoImpl.getInstance();
    }

    public void init(Client client) {
        Collection<Targeta> targetes = this.targetaDao.findByIdClient(client);
        client.setTargetes(targetes);
        PantallaPagamentView.getInstance().init(client);
    }

    public void nextAction(Client client, String option) {
        switch (option) {
            case "0":
            case "1":
            case "2": client.getCompra().setTargeta((Targeta)client.getTargetes().toArray()[Integer.parseInt(option)]); PantallaEnviamentController.getInstance().init(client);
            case "a": PantallaCreateTargetaController.getInstance().init(client, "pagament"); break;
            case "b": this.init(client); break;
            case "x": PantallaCistellaController.getInstance().init(client); break;
        }
    }

    public void deleteTargeta(Targeta targeta) {
        this.compraDao.updateByTargeta(targeta);
        this.targetaDao.delete(targeta);
    }

    public synchronized static PantallaPagamentController getInstance() {
        if (instance == null) {
            instance = new PantallaPagamentController();
        }
        return instance;
    }
}
