package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.ProducteDao;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.dao.impl.ProducteDaoImpl;
import es.cc.esliceu.db.limbo.model.Cistella;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Producte;
import es.cc.esliceu.db.limbo.views.PantallaPrincipalView;

import java.util.Collection;

public class PantallaPrincipalController {

    private final ProducteDao producteDao;

    public PantallaPrincipalController() {
        this.producteDao = new ProducteDaoImpl(DBConnectionImpl.getInstance());
    }

    public void init(Client client) {
        PantallaPrincipalView pantallaPrincipalView = new PantallaPrincipalView();
        if (client.getCistella() == null) {
            Cistella cistella = new Cistella();
            client.setCistella(cistella);
        }
        Collection<Producte> suggestedProducts = this.getProductesSuggerits();
        pantallaPrincipalView.init(client, suggestedProducts);
    }

    public Collection<Producte> getProductesSuggerits() {
        return this.producteDao.findSuggested();
    }

    public void nextPage(String option, Client client) {
        switch (option) {
            case "c": PantallaCercaProductesController pantallaCercaProductesController = new PantallaCercaProductesController(); pantallaCercaProductesController.init(client) ;break;
            case "v": PantallaCistellaController pantallaCistellaController = new PantallaCistellaController(); pantallaCistellaController.init(client); break;
            case "d": PantallaClientController pantallaClientController = new PantallaClientController(); pantallaClientController.init(client); break;
            case "h": PantallaAjudaController pantallaAjudaController = new PantallaAjudaController(); pantallaAjudaController.init(client); break;
            case "x": PantallaInicialController pantallaInicialController = new PantallaInicialController(); pantallaInicialController.init(); break;
        }
    }
}
