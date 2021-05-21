package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.AdrecaDao;
import es.cc.esliceu.db.limbo.dao.impl.*;
import es.cc.esliceu.db.limbo.model.Adreca;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Targeta;
import es.cc.esliceu.db.limbo.views.PantallaEnviamentView;

import java.util.Collection;

public class PantallaEnviamentController {

    private final AdrecaDao adrecaDao;

    public PantallaEnviamentController() {
        this.adrecaDao = new AdrecaDaoImpl(DBConnectionImpl.getInstance());
    }

    public void init(Client client, Targeta targeta) {
        PantallaEnviamentView pantallaEnviamentView = new PantallaEnviamentView();
        Collection<Adreca> adreces = this.adrecaDao.findAllByIdClient(client);
        client.setAdreces(adreces);
        pantallaEnviamentView.init(client, targeta);
    }

    public void nextAction(Client client, Targeta targeta, String option) {
        PantallaConfirmacioCompraController pantallaConfirmacioCompraController = null;
        if (!option.equals("x") && !option.equals("a")) {
            if (Integer.parseInt(option) <= 2) pantallaConfirmacioCompraController = new PantallaConfirmacioCompraController();
        }
        switch (option) {
            case "0": pantallaConfirmacioCompraController.init(client, targeta, (Adreca)client.getAdreces().toArray()[Integer.parseInt(option)]); break;
            case "1": pantallaConfirmacioCompraController.init(client, targeta, (Adreca)client.getAdreces().toArray()[Integer.parseInt(option)]); break;
            case "2": pantallaConfirmacioCompraController.init(client, targeta, (Adreca)client.getAdreces().toArray()[Integer.parseInt(option)]); break;
            case "a": PantallaCreateAdrecaController pantallaCreateAdrecaController = new PantallaCreateAdrecaController(); pantallaCreateAdrecaController.init(client, "enviament", targeta); break;
            case "x": PantallaPagamentController pantallaPagamentController = new PantallaPagamentController(); pantallaPagamentController.init(client); break;
        }
    }
}