package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.views.PantallaAjudaView;

public class PantallaAjudaController {


    private static PantallaAjudaController instance;

    private PantallaAjudaController() {
    }

    public void init(Client client) {
        PantallaAjudaView.getInstance().init(client);
    }

    public void init() {
        PantallaAjudaView.getInstance().init(null);
    }

    public void goBack(Client client) {
        if (client == null) {
            PantallaInicialController.getInstance().init();
        } else {
            PantallaPrincipalController.getInstance().init(client);
        }
    }

    public synchronized static PantallaAjudaController getInstance() {
        if (instance == null) {
            instance = new PantallaAjudaController();
        }
        return instance;
    }
}
