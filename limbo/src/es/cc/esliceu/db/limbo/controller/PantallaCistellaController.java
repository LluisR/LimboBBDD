package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.DetallCompra;
import es.cc.esliceu.db.limbo.views.PantallaCistellaView;

import java.util.concurrent.atomic.AtomicInteger;

public class PantallaCistellaController {

    private static PantallaCistellaController instance;

    private PantallaCistellaController() {
    }

    public void init(Client client) {
        PantallaCistellaView.getInstance().init(client);
    }

    public void dropProduct(Client client, DetallCompra producte, String unitats) {
        if (unitats.length() == 0 || producte.getUnitats_producte() <= Integer.parseInt(unitats)) {
            client.getCompra().getProductes().remove(producte);
        } else {
            producte.setUnitats_producte(producte.getUnitats_producte()-Integer.parseInt(unitats));
        }
        PantallaCistellaView.getInstance().init(client);
    }

    public void nextAction(Client client, String option, AtomicInteger idx) {
        switch (option) {
            case "e": PantallaCistellaView.getInstance().deleteProducte(client, idx); break;
            case "p": PantallaPagamentController.getInstance().init(client); break;
            case "x": PantallaPrincipalController.getInstance().init(client); break;
        }
    }

    public synchronized static PantallaCistellaController getInstance() {
        if (instance == null) {
            instance = new PantallaCistellaController();
        }
        return instance;
    }
}
