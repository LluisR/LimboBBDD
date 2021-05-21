package es.cc.esliceu.db.limbo.controller;


import es.cc.esliceu.db.limbo.dao.AdrecaDao;
import es.cc.esliceu.db.limbo.dao.TargetaDao;
import es.cc.esliceu.db.limbo.dao.impl.AdrecaDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.dao.impl.TargetaDaoImpl;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.views.PantallaClientView;

public class PantallaClientController {

    private final AdrecaDao adrecaDao;
    private final TargetaDao targetaDao;

    public PantallaClientController() {

        this.adrecaDao = new AdrecaDaoImpl(DBConnectionImpl.getInstance());
        this.targetaDao = new TargetaDaoImpl(DBConnectionImpl.getInstance());
    }

    public void init(Client client) {
        PantallaClientView pantallaClientView = new PantallaClientView();
        pantallaClientView.init(client);
    }

    public void nextAction(Client client, String option) {
        PantallaSettingsController pantallaSettingsController = null;
        if (option.equals("d") || option.equals("a")) {
            pantallaSettingsController = new PantallaSettingsController();
        }
        switch (option) {
            case "d": pantallaSettingsController.init(client); break;
            case "c": PantallaCompresRealitzadesController pantallaCompresRealitzadesController = new PantallaCompresRealitzadesController(); pantallaCompresRealitzadesController.init(client); break;
            case "a": PantallaClientView pantallaClientView = new PantallaClientView(); client.setAdreces(this.adrecaDao.findAllByIdClient(client)); pantallaClientView.showAdress(client); break;
            case "t": PantallaClientView pantallaClientView1 = new PantallaClientView(); client.setTargetes(this.targetaDao.findByIdClient(client)); pantallaClientView1.showTargetes(client); break;
            case "x": PantallaPrincipalController pantallaPrincipalController = new PantallaPrincipalController(); pantallaPrincipalController.init(client); break;
        }
    }

    public void nextAddressAction(Client client, String option) {
        switch (option) {
            case "a": PantallaCreateAdrecaController pantallaCreateAdrecaController = new PantallaCreateAdrecaController(); pantallaCreateAdrecaController.init(client, "settings", null); break;
            case "x": this.init(client); break;
        }
    }

    public void nextTargetesAction(Client client, String option) {
        switch (option) {
            case "a": PantallaCreateTargetaController pantallaCreateTargetaController = new PantallaCreateTargetaController(); pantallaCreateTargetaController.init(client, "settings"); break;
            case "x": this.init(client); break;
        }
    }

    public void nextCompresRealitzadesAction(Client client, String option) {
        switch (option) {
            case "x": this.init(client); break;
        }
    }
}
