package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.EnviadorEmail;
import es.cc.esliceu.db.limbo.GeneradorHash;
import es.cc.esliceu.db.limbo.dao.CompraDao;
import es.cc.esliceu.db.limbo.dao.DetallCompraDao;
import es.cc.esliceu.db.limbo.dao.impl.CompraDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.dao.impl.DetallCompraDaoImpl;
import es.cc.esliceu.db.limbo.model.*;
import es.cc.esliceu.db.limbo.views.PantallaConfirmacioCompraView;

import java.util.Date;

public class PantallaConfirmacioCompraController {

    private final CompraDao compraDao;
    private final DetallCompraDao detallCompraDao;

    public PantallaConfirmacioCompraController() {
        this.compraDao = new CompraDaoImpl(DBConnectionImpl.getInstance());
        this.detallCompraDao = new DetallCompraDaoImpl(DBConnectionImpl.getInstance());
    }

    public void init(Client client) {
        PantallaConfirmacioCompraView pantallaConfirmacioCompraView = new PantallaConfirmacioCompraView();
        client.setCompres(this.compraDao.findByIdClient(client));
        pantallaConfirmacioCompraView.init(client);
    }

    public void nextAction(Client client, String option) {
        switch (option) {
            case "s": this.purchase(client); break;
            case "n": PantallaEnviamentController pantallaEnviamentController = new PantallaEnviamentController(); pantallaEnviamentController.init(client); break;
        }
    }

    private void purchase(Client client) {
        Date date = new Date(System.currentTimeMillis());
        String transaccio = GeneradorHash.generaRandomString();
        client.getCompra().setData(date);
        client.getCompra().setId_transaccio(transaccio);
        Integer idCompra = this.compraDao.save(client.getCompra());
        client.getCompra().setId(idCompra);
        client.getCompra().getProductes().forEach(detallCompra -> {
            detallCompra.setCompra(client.getCompra());
            detallCompra.setPvp(detallCompra.getProducte().getPvp());
            detallCompra.setPes(detallCompra.getPes());
            this.detallCompraDao.save(detallCompra);
        });
        String resum = generaResumEmail(client);
        EnviadorEmail.enviaEmail(client.getEmail(), "Confirmació de compra: " + client.getCompra().getId_transaccio(), resum);
        PantallaConfirmacioCompraView pantallaConfirmacioCompraView = new PantallaConfirmacioCompraView();
        pantallaConfirmacioCompraView.confirmacioCompra(client);
        Compra newCompra = new Compra();
        newCompra.setClient(client);
        client.setCompra(newCompra);
    }

    private String generaResumEmail(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append("Compra " + client.getCompra().getData());
        sb.append("\n");
        sb.append("ID transacció: " + client.getCompra().getId_transaccio());
        sb.append("\n");
        sb.append("Client: " + client.getNom() + " " + client.getCognom1() + " " + client.getCognom2() + " " + client.getEmail());
        sb.append("\n");
        client.getCompra().getProductes().forEach(detallCompra -> {
            sb.append(detallCompra.getProducte().getNom() + " " + detallCompra.getUnitats_producte() + " " + detallCompra.getPvp() + "€");
            sb.append("\n");
        });
        sb.append("TOTAL: " + client.getCompra().getTotal() + "€");
        sb.append("\n");
        sb.append("Adreça d'enviament: ");
        sb.append("\n");
        sb.append(client.getCompra().getAdreca().getCarrer() + " " + client.getCompra().getAdreca().getNumero() + " " + client.getCompra().getAdreca().getCp());
        sb.append("\n");
        sb.append("Targeta: ");
        sb.append(client.getCompra().getTargeta().getNumero() +  " " + client.getCompra().getTargeta().getTipus() +  " " + client.getCompra().getTargeta().getData_caducitat());

        return sb.toString();
    }

    public void goHome(Client client) {
        PantallaPrincipalController pantallaPrincipalController = new PantallaPrincipalController();
        pantallaPrincipalController.init(client);
    }
}
