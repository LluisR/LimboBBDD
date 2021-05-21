package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.CategoriaDao;
import es.cc.esliceu.db.limbo.dao.ProducteDao;
import es.cc.esliceu.db.limbo.dao.impl.CategoriaDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.dao.impl.ProducteDaoImpl;
import es.cc.esliceu.db.limbo.model.Categoria;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.DetallCompra;
import es.cc.esliceu.db.limbo.model.Producte;
import es.cc.esliceu.db.limbo.views.PantallaCercaProductesView;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PantallaCercaProductesController {

    private final ProducteDao producteDao;
    private final CategoriaDao categoriaDao;

    public PantallaCercaProductesController() {
        this.producteDao = new ProducteDaoImpl(DBConnectionImpl.getInstance());
        this.categoriaDao = new CategoriaDaoImpl(DBConnectionImpl.getInstance());
    }

    public void init(Client client) {
        PantallaCercaProductesView pantallaCercaProductesView = new PantallaCercaProductesView();
        Collection<Categoria> categories = this.categoriaDao.findAll();
        pantallaCercaProductesView.init(client, categories);
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
        PantallaCercaProductesView pantallaCercaProductesView = new PantallaCercaProductesView();
        pantallaCercaProductesView.resultProducts(client, productes);

    }

    public void addToCistella(Client client, Producte producte, Integer unitats) {

        DetallCompra detallCompra = client.getCompra().getProductes().stream()
                .filter(prod -> producte.getNom().equals(prod.getProducte().getNom()))
                .findAny()
                .orElse(null);

        if (detallCompra != null) detallCompra.setUnitats_producte(detallCompra.getUnitats_producte() + unitats);
        else {
            DetallCompra newDetallCompra = new DetallCompra();
            newDetallCompra.setProducte(producte);
            newDetallCompra.setUnitats_producte(unitats);
            Collection<DetallCompra> detalls = client.getCompra().getProductes();
            detalls.add(newDetallCompra);
            client.getCompra().setProductes(detalls);
        }
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
