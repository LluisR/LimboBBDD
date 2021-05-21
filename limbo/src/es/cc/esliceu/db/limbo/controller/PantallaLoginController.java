package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.GeneradorHash;
import es.cc.esliceu.db.limbo.dao.ClientDao;
import es.cc.esliceu.db.limbo.dao.impl.ClientDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.views.PantallaLoginView;

public class PantallaLoginController {

    private final ClientDao clientDao;

    public PantallaLoginController() {
        this.clientDao = new ClientDaoImpl(DBConnectionImpl.getInstance());
    }

    public void init() {
        PantallaLoginView pantallaLoginView = new PantallaLoginView();
        pantallaLoginView.init();
    }

    public Client checkIfExistsUsername(String username) {
        Client client = this.clientDao.findByUserName(username);
        return client;
    }

    public boolean checkIfPasswordsMatches(Client client, String password) {
        String passwordHash = GeneradorHash.generaHash(password);
        return passwordHash.equals(client.getContrasenya());
    }

    public void loginSuccessful(Client client) {
        PantallaPrincipalController pantallaPrincipalController = new PantallaPrincipalController();
        pantallaPrincipalController.init(client);
    }
}
