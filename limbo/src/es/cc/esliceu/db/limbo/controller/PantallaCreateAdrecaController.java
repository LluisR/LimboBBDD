package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.AdrecaDao;
import es.cc.esliceu.db.limbo.dao.impl.AdrecaDaoImpl;
import es.cc.esliceu.db.limbo.model.Adreca;
import es.cc.esliceu.db.limbo.model.Ciutat;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.views.PantallaCreateAdrecaView;

public class PantallaCreateAdrecaController {

    private static PantallaCreateAdrecaController instance;
    private final AdrecaDao adrecaDao;

    private PantallaCreateAdrecaController() {
        this.adrecaDao = AdrecaDaoImpl.getInstance();
    }

    public void init(Client client, String from) {
        PantallaCreateAdrecaView.getInstance().init(client, from);
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

    public void nextAction(Client client, String from) {
        if (from.equals("settings")) {
            PantallaSettingsController.getInstance().init(client);
        } else if (from.equals("enviament")) {
            PantallaEnviamentController.getInstance().init(client);
        }
    }

    public synchronized static PantallaCreateAdrecaController getInstance() {
        if (instance == null) {
            instance = new PantallaCreateAdrecaController();
        }
        return instance;
    }
}
