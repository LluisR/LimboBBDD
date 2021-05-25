package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.AdrecaDao;
import es.cc.esliceu.db.limbo.dao.CompraDao;
import es.cc.esliceu.db.limbo.dao.impl.*;
import es.cc.esliceu.db.limbo.model.Adreca;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.views.PantallaEnviamentView;

import java.util.Collection;

public class PantallaEnviamentController {

    private final AdrecaDao adrecaDao;
    private final CompraDao compraDao;

    public PantallaEnviamentController() {
        this.adrecaDao = AdrecaDaoImpl.getInstance();
        this.compraDao = CompraDaoImpl.getInstance();
    }

    public void init(Client client) {
        Collection<Adreca> adreces = this.adrecaDao.findAllByIdClient(client);
        client.setAdreces(adreces);
        PantallaEnviamentView.getInstance().init(client);
    }

    public void nextAction(Client client, String option) {
        switch (option) {
            case "0":
            case "1":
            case "2": client.getCompra().setAdreca((Adreca)client.getAdreces().toArray()[Integer.parseInt(option)]);PantallaConfirmacioCompraController pantallaConfirmacioCompraController = new PantallaConfirmacioCompraController(); pantallaConfirmacioCompraController.init(client); break;
            case "a": PantallaCreateAdrecaController pantallaCreateAdrecaController = new PantallaCreateAdrecaController(); pantallaCreateAdrecaController.init(client, "enviament"); break;
            case "b": this.init(client); break;
            case "x": PantallaPagamentController pantallaPagamentController = new PantallaPagamentController(); pantallaPagamentController.init(client); break;
        }
    }

    public void deleteAdress(Adreca adreca) {
        this.compraDao.updateByAdress(adreca);
        this.adrecaDao.delete(adreca);
    }
}