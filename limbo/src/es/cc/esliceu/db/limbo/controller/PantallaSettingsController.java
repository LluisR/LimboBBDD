package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.GeneradorHash;
import es.cc.esliceu.db.limbo.dao.ClientDao;
import es.cc.esliceu.db.limbo.dao.impl.ClientDaoImpl;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.views.PantallaSettingsView;

public class PantallaSettingsController {

    private static PantallaSettingsController instance;
    private final ClientDao clientDao;

    private PantallaSettingsController() {
        this.clientDao = ClientDaoImpl.getInstance();
    }

    public void init(Client client) {
        PantallaSettingsView.getInstance().init(client);
    }

    public void nextAction(Client client, String option) {
        switch (option) {
            case "a": PantallaSettingsView.getInstance().modifyClient(client);
            case "b": PantallaSettingsView.getInstance().changePassword(client);
            case "x": PantallaClientController.getInstance().init(client); break;
            default: PantallaPrincipalController.getInstance().init(client); break;
        }
    }

    public void modifyClient(Client client) {
        clientDao.update(client);
    }

    public boolean checkOldPassword(Client client, String oldPassword) {
        return client.getContrasenya().equals(GeneradorHash.generaHash(oldPassword));
    }

    public synchronized static PantallaSettingsController getInstance() {
        if (instance == null) {
            instance = new PantallaSettingsController();
        }
        return instance;
    }
}
