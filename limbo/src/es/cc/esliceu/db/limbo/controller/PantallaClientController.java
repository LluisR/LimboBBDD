package es.cc.esliceu.db.limbo.controller;


import es.cc.esliceu.db.limbo.dao.AdrecaDao;
import es.cc.esliceu.db.limbo.dao.CompraDao;
import es.cc.esliceu.db.limbo.dao.TargetaDao;
import es.cc.esliceu.db.limbo.dao.impl.AdrecaDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.CompraDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.dao.impl.TargetaDaoImpl;
import es.cc.esliceu.db.limbo.model.Adreca;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Targeta;
import es.cc.esliceu.db.limbo.views.PantallaCercaProductesView;
import es.cc.esliceu.db.limbo.views.PantallaClientView;

public class PantallaClientController {

    private static PantallaClientController instance;
    private final AdrecaDao adrecaDao;
    private final TargetaDao targetaDao;
    private final CompraDao compraDao;

    private PantallaClientController() {
        this.adrecaDao = AdrecaDaoImpl.getInstance();
        this.targetaDao = TargetaDaoImpl.getInstance();
        this.compraDao = CompraDaoImpl.getInstance();
    }

    public void init(Client client) {
        PantallaClientView.getInstance().init(client);
    }

    public void nextAction(Client client, String option) {
        switch (option) {
            case "d": PantallaSettingsController.getInstance().init(client); break;
            case "c": PantallaCompresRealitzadesController.getInstance().init(client); break;
            case "a": client.setAdreces(this.adrecaDao.findAllByIdClient(client)); PantallaClientView.getInstance().showAdress(client); break;
            case "t": client.setTargetes(this.targetaDao.findByIdClient(client)); PantallaClientView.getInstance().showTargetes(client); break;
            case "x": PantallaPrincipalController.getInstance().init(client); break;
        }
    }

    public void nextAddressAction(Client client, String option) {
        switch (option) {
            case "a": PantallaCreateAdrecaController.getInstance().init(client, "settings"); break;
            case "b":
            case "x": this.init(client); break;
        }
    }

    public void nextTargetesAction(Client client, String option) {
        switch (option) {
            case "a": PantallaCreateTargetaController.getInstance().init(client, "settings"); break;
            case "b":
            case "x": this.init(client); break;
        }
    }

    public void deleteTargeta(Client client, Integer indexTargeta) {
        this.compraDao.updateByTargeta((Targeta) client.getTargetes().toArray()[indexTargeta]);
        this.targetaDao.delete((Targeta) client.getTargetes().toArray()[indexTargeta]);
    }

    public void deleteAdress(Client client, Integer indexAdress) {
        this.compraDao.updateByAdress((Adreca) client.getAdreces().toArray()[indexAdress]);
        this.adrecaDao.delete((Adreca) client.getAdreces().toArray()[indexAdress]);
    }

    public synchronized static PantallaClientController getInstance() {
        if (instance == null) {
            instance = new PantallaClientController();
        }
        return instance;
    }
}
