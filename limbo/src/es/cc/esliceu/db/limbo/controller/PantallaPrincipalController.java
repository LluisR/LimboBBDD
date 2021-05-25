package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.DescompteClientDao;
import es.cc.esliceu.db.limbo.dao.DescompteDao;
import es.cc.esliceu.db.limbo.dao.DescompteProducteDao;
import es.cc.esliceu.db.limbo.dao.ProducteDao;
import es.cc.esliceu.db.limbo.dao.impl.*;
import es.cc.esliceu.db.limbo.model.*;
import es.cc.esliceu.db.limbo.views.PantallaPrincipalView;

import java.text.SimpleDateFormat;
import java.util.*;

public class PantallaPrincipalController {

    private final ProducteDao producteDao;
    private final DescompteDao descompteDao;
    private final DescompteClientDao descompteClientDao;
    private final DescompteProducteDao descompteProducteDao;

    public PantallaPrincipalController() {
        this.producteDao = ProducteDaoImpl.getInstance();
        this.descompteDao = DescompteDaoImpl.getInstance();
        this.descompteClientDao = DescompteClientDaoImpl.getInstance();
        this.descompteProducteDao = DescompteProducteDaoImpl.getInstance();
    }

    public void init(Client client) {
        if (client.getCompra() == null) {
            Compra compra = new Compra();
            compra.setClient(client);
            client.setCompra(compra);
        }
        if (client.getDescomptes() == null) {
            client.setDescomptes(this.getDiscounts(client));
        }
        Collection<Producte> suggestedProducts = this.getProductesSuggerits();
        PantallaPrincipalView.getInstance().init(client, suggestedProducts);
    }

    public Collection<Producte> getProductesSuggerits() {
        return this.producteDao.findSuggested();
    }

    private Map<Descompte, Collection<Producte>> getDiscounts(Client client) {
        Collection<DescompteClient> descomptesClient = this.descompteClientDao.findAllByIdClient(client);
        Collection<Descompte> descomptes = new ArrayList<>();
        descomptesClient.forEach(descompteClient -> {
            Descompte descompte = this.descompteDao.findById(descompteClient.getDescompte());
            if (descompte.getDataInici().compareTo(new Date()) < 0 && descompte.getDataFi().compareTo(new Date()) > 0) descomptes.add(descompte);
        });

        Map<Descompte, Collection<Producte>> descompteProducteMap = new HashMap<>();
        descomptes.forEach(descompte -> {
            Collection<DescompteProducte> descompteProductes = this.descompteProducteDao.findAllByIdDescompte(descompte);
            Collection<Producte> productes = new ArrayList<>();
            descompteProductes.forEach(descompteProducte -> {
                Producte producte = this.producteDao.findById(descompteProducte.getProducte().getId());
                productes.add(producte);
                System.out.println(producte);
            });
            descompteProducteMap.put(descompte, productes);
        });
        return descompteProducteMap;
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
