package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.GeneradorHash;
import es.cc.esliceu.db.limbo.dao.ClientDao;
import es.cc.esliceu.db.limbo.dao.impl.ClientDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.views.PantallaSettingsView;

public class PantallaSettingsController {

    private final ClientDao clientDao;

    public PantallaSettingsController() {
        this.clientDao = new ClientDaoImpl(DBConnectionImpl.getInstance());
    }

    public void init(Client client) {
        PantallaSettingsView pantallaSettingsView = new PantallaSettingsView();
        pantallaSettingsView.init(client);
    }

    public void nextAction(Client client, String option) {
        PantallaSettingsView pantallaSettingsView = null;
        if (option.equals("a") || option.equals("b")) {
            pantallaSettingsView = new PantallaSettingsView();
        }
        switch (option) {
            case "a": pantallaSettingsView.modifyClient(client);
            case "b": pantallaSettingsView.changePassword(client);
            case "x": PantallaClientController pantallaClientController = new PantallaClientController(); pantallaClientController.init(client); break;
            default: PantallaPrincipalController pantallaPrincipalController = new PantallaPrincipalController(); pantallaPrincipalController.init(client); break;
        }
    }

    public void modifyClient(Client client) {
        clientDao.update(client);
    }

    public void changePassword(Client client) {

    }

    public boolean checkOldPassword(Client client, String oldPassword) {
        return client.getContrasenya().equals(GeneradorHash.generaHash(oldPassword));
    }
}
