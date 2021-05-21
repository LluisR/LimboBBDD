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

    public void init(Client client, Targeta targeta, Adreca adreca) {
        PantallaConfirmacioCompraView pantallaConfirmacioCompraView = new PantallaConfirmacioCompraView();
        client.setCompres(this.compraDao.findByIdClient(client));
        pantallaConfirmacioCompraView.init(client, targeta, adreca);
    }

    public void nextAction(Client client, Targeta targeta, Adreca adreca, String option) {
        switch (option) {
            case "s": this.purchase(client, targeta, adreca); break;
            case "n": PantallaEnviamentController pantallaEnviamentController = new PantallaEnviamentController(); pantallaEnviamentController.init(client, targeta); break;
        }
    }

    private void purchase(Client client, Targeta targeta, Adreca adreca) {
        Date date = new Date(System.currentTimeMillis());
        String transaccio = GeneradorHash.generaRandomString();
        Compra compra = new Compra();
        compra.setClient(client);
        compra.setTargeta(targeta);
        compra.setAdreca(adreca);
        compra.setData(date);
        compra.setId_transaccio(transaccio);
        Integer idCompra = this.compraDao.save(compra);
        compra.setId(idCompra);
        client.getCistella().getProductes().forEach(detallCompra -> {
            detallCompra.setCompra(compra);
            detallCompra.setPvp(detallCompra.getProducte().getPvp());
            detallCompra.setPes(detallCompra.getPes());
            this.detallCompraDao.save(detallCompra);
        });
        String resum = generaResumEmail(client, compra);
        //EnviadorEmail.enviaEmail(client.getEmail(), "Confirmació de compra: " + compra.getId_transaccio(), resum);
        client.setCistella(new Cistella());
        PantallaConfirmacioCompraView pantallaConfirmacioCompraView = new PantallaConfirmacioCompraView();
        pantallaConfirmacioCompraView.confirmacioCompra(client, compra);
    }

    private String generaResumEmail(Client client, Compra compra) {
        StringBuilder sb = new StringBuilder();
        sb.append("Compra " + compra.getData());
        sb.append("\n");
        sb.append("ID transacció: " + compra.getId_transaccio());
        sb.append("\n");
        sb.append("Client: " + client.getNom() + " " + client.getCognom1() + " " + client.getCognom2() + " " + client.getEmail());
        sb.append("\n");
        client.getCistella().getProductes().forEach(detallCompra -> {
            sb.append(detallCompra.getProducte().getNom() + " " + detallCompra.getUnitats_producte() + " " + detallCompra.getPvp() + "€");
            sb.append("\n");
        });
        sb.append("TOTAL: " + client.getCistella().getTotal() + "€");
        sb.append("\n");
        sb.append("Adreça d'enviament: ");
        sb.append("\n");
        sb.append(compra.getAdreca().getCarrer() + " " + compra.getAdreca().getNumero() + " " + compra.getAdreca().getCp());
        sb.append("\n");
        sb.append("Targeta: ");
        sb.append(compra.getTargeta().getNumero() +  " " + compra.getTargeta().getTipus() +  " " + compra.getTargeta().getData_caducitat());

        return sb.toString();
    }

    public void goHome(Client client) {
        PantallaPrincipalController pantallaPrincipalController = new PantallaPrincipalController();
        pantallaPrincipalController.init(client);
    }
}
