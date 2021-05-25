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
import es.cc.esliceu.db.limbo.views.PantallaClientView;

public class PantallaClientController {

    private final AdrecaDao adrecaDao;
    private final TargetaDao targetaDao;
    private final CompraDao compraDao;

    public PantallaClientController() {
        this.adrecaDao = AdrecaDaoImpl.getInstance();
        this.targetaDao = TargetaDaoImpl.getInstance();
        this.compraDao = CompraDaoImpl.getInstance();
    }

    public void init(Client client) {
        PantallaClientView.getInstance().init(client);
    }

    public void nextAction(Client client, String option) {
        PantallaSettingsController pantallaSettingsController = null;
        if (option.equals("d") || option.equals("a")) {
            pantallaSettingsController = new PantallaSettingsController();
        }
        switch (option) {
            case "d": pantallaSettingsController.init(client); break;
            case "c": PantallaCompresRealitzadesController pantallaCompresRealitzadesController = new PantallaCompresRealitzadesController(); pantallaCompresRealitzadesController.init(client); break;
            case "a": client.setAdreces(this.adrecaDao.findAllByIdClient(client)); PantallaClientView.getInstance().showAdress(client); break;
            case "t": client.setTargetes(this.targetaDao.findByIdClient(client)); PantallaClientView.getInstance().showTargetes(client); break;
            case "x": PantallaPrincipalController pantallaPrincipalController = new PantallaPrincipalController(); pantallaPrincipalController.init(client); break;
        }
    }

    public void nextAddressAction(Client client, String option) {
        switch (option) {
            case "a": PantallaCreateAdrecaController pantallaCreateAdrecaController = new PantallaCreateAdrecaController(); pantallaCreateAdrecaController.init(client, "settings"); break;
            case "b":
            case "x": this.init(client); break;
        }
    }

    public void nextTargetesAction(Client client, String option) {
        switch (option) {
            case "a": PantallaCreateTargetaController pantallaCreateTargetaController = new PantallaCreateTargetaController(); pantallaCreateTargetaController.init(client, "settings"); break;
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
}
