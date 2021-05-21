package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.AdrecaDao;
import es.cc.esliceu.db.limbo.dao.impl.AdrecaDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.model.Adreca;
import es.cc.esliceu.db.limbo.model.Ciutat;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Targeta;
import es.cc.esliceu.db.limbo.views.PantallaCreateAdrecaView;

public class PantallaCreateAdrecaController {

    private final AdrecaDao adrecaDao;

    public PantallaCreateAdrecaController() {
        this.adrecaDao = new AdrecaDaoImpl(DBConnectionImpl.getInstance());
    }

    public void init(Client client, String from, Targeta targeta) {
        PantallaCreateAdrecaView pantallaCreateAdrecaView = new PantallaCreateAdrecaView();
        pantallaCreateAdrecaView.init(client, from, targeta);
    }

    public void saveNewAdreca(Client client, String carrer, String numero, Integer pis, String porta, String cp) {
        Adreca adreca = new Adreca();
        adreca.setCarrer(carrer);
        adreca.setNumero(numero);
        adreca.setPis(pis);
        adreca.setPorta(porta);
        adreca.setCp(cp);
        adreca.setClient(client);
        Ciutat ciutat = new Ciutat();
        ciutat.setId(1);
        adreca.setCiutat(ciutat);
        client.getAdreces().add(adreca);
        this.adrecaDao.save(adreca);
    }

    public void nextAction(Client client, String from, Targeta targeta) {
        if (from.equals("settings")) {
            PantallaSettingsController pantallaSettingsController = new PantallaSettingsController(); pantallaSettingsController.init(client);
        } else if (from.equals("enviament")) {
            PantallaEnviamentController pantallaEnviamentController = new PantallaEnviamentController(); pantallaEnviamentController.init(client, targeta);
        }
    }
}
