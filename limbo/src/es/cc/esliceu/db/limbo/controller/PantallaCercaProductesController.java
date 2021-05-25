package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.*;
import es.cc.esliceu.db.limbo.dao.impl.*;
import es.cc.esliceu.db.limbo.model.*;
import es.cc.esliceu.db.limbo.views.PantallaCercaProductesView;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class PantallaCercaProductesController {

    private final ProducteDao producteDao;
    private final CategoriaDao categoriaDao;

    public PantallaCercaProductesController() {
        this.producteDao = ProducteDaoImpl.getInstance();
        this.categoriaDao = CategoriaDaoImpl.getInstance();
    }

    public void init(Client client) {
        Collection<Categoria> categories = this.categoriaDao.findAll();
        PantallaCercaProductesView.getInstance().init(client, categories);
    }

    public void searchProducts(Client client, String nom, String descripcio, String marca, String categoria) {
        Map<String,Integer> mapaFiltres = new HashMap<>();
        int params = 1;
        String sql = "select * from producte where 1=1";

        if (nom.length() > 0) {
            sql += " and nom like ( ? )";
            mapaFiltres.put("nomProducte", params);
            nom = "%" + nom + "%";
            params++;
        }
        if (descripcio.length() > 0) {
            sql += " and descripcio like ( ? )";
            mapaFiltres.put("descripcioProducte", params);
            descripcio = "%" + descripcio + "%";
            params++;
        }
        if (marca.length() > 0) {
            sql += " and marca like ( ? )";
            mapaFiltres.put("marcaProducte", params);
            marca = "%" + marca + "%";
            params++;
        }
        if (categoria.length() > 0) {
            sql += " and categoria = ?";
            mapaFiltres.put("categoria", params);
            params++;
        }

        Collection<Producte> productes = this.producteDao.findWithFilters(mapaFiltres, sql, nom, descripcio, marca, categoria);
        PantallaCercaProductesView.getInstance().resultProducts(client, productes);

    }

    public void addToCistella(Client client, Producte producte, Integer unitats) {

        DetallCompra detallCompra = client.getCompra().getProductes().stream()
                .filter(prod -> producte.getId().equals(prod.getProducte().getId()))
                .findAny()
                .orElse(null);

        if (detallCompra != null) detallCompra.setUnitats_producte(detallCompra.getUnitats_producte() + unitats);
        else {
            DetallCompra newDetallCompra = new DetallCompra();
            newDetallCompra.setProducte(producte);
            newDetallCompra.setUnitats_producte(unitats);
            newDetallCompra.setPes(producte.getPes());
            newDetallCompra.setPvp(this.addDiscount(client, newDetallCompra));
            Collection<DetallCompra> detalls = client.getCompra().getProductes();
            detalls.add(newDetallCompra);
            client.getCompra().setProductes(detalls);
        }
    }

    private Double addDiscount(Client client, DetallCompra detallCompra) {
        AtomicReference<Double> finalPrice = new AtomicReference<>(detallCompra.getProducte().getPvp());
        client.getDescomptes().forEach((descompte, productes) -> {
            productes.forEach(producteDis -> {
                if (detallCompra.getProducte().getId() == producteDis.getId()) {
                    finalPrice.set(detallCompra.getProducte().getPvp() * (100-descompte.getPercentatge())/100);
                    detallCompra.setPercentatge(descompte.getPercentatge());
                }
            });
        });
        return Double.parseDouble(finalPrice.toString());
    }

    public void goToCistella(Client client) {
        PantallaCistellaController pantallaCistellaController = new PantallaCistellaController();
        pantallaCistellaController.init(client);
    }

    public void nextAction(Client client, String option) {
        switch (option) {
            case "f": this.goToCistella(client); break;
            case "c": this.init(client); break;
            case "x": PantallaPrincipalController pantallaPrincipalController = new PantallaPrincipalController(); pantallaPrincipalController.init(client); break;
        }
    }

    public void goBack(Client client) {
        PantallaPrincipalController pantallaPrincipalController = new PantallaPrincipalController();
        pantallaPrincipalController.init(client);
    }
}
