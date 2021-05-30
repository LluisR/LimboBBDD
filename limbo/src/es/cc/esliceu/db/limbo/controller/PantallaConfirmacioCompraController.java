package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.EnviadorEmail;
import es.cc.esliceu.db.limbo.GeneradorHash;
import es.cc.esliceu.db.limbo.dao.CompraDao;
import es.cc.esliceu.db.limbo.dao.DBConnection;
import es.cc.esliceu.db.limbo.dao.DetallCompraDao;
import es.cc.esliceu.db.limbo.dao.impl.CompraDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.dao.impl.DetallCompraDaoImpl;
import es.cc.esliceu.db.limbo.model.*;
import es.cc.esliceu.db.limbo.views.PantallaConfirmacioCompraView;

import java.sql.SQLException;
import java.util.Date;

public class PantallaConfirmacioCompraController {

    private static PantallaConfirmacioCompraController instance;
    private final CompraDao compraDao;
    private final DetallCompraDao detallCompraDao;

    private PantallaConfirmacioCompraController() {
        this.compraDao = CompraDaoImpl.getInstance();
        this.detallCompraDao = DetallCompraDaoImpl.getInstance();
    }

    public void init(Client client) {
        client.setCompres(this.compraDao.findByIdClient(client));
        PantallaConfirmacioCompraView.getInstance().init(client);
    }

    public void nextAction(Client client, String option) {
        switch (option) {
            case "s": this.purchase(client); break;
            case "n": PantallaEnviamentController.getInstance().init(client); break;
        }
    }

    private void purchase(Client client) {
        Date date = new Date(System.currentTimeMillis());
        String transaccio = GeneradorHash.generaRandomString();
        try {
            DBConnectionImpl.getInstance().getConnection().setAutoCommit(false);
            client.getCompra().setData(date);
            client.getCompra().setId_transaccio(transaccio);
            Integer idCompra = this.compraDao.save(client.getCompra());
            client.getCompra().setId(idCompra);
            client.getCompra().getProductes().forEach(detallCompra -> {
                detallCompra.setCompra(client.getCompra());
                if (detallCompra.getPvp() == null) {
                    detallCompra.setPvp(detallCompra.getProducte().getPvp());
                }
                detallCompra.setPes(detallCompra.getPes());
                this.detallCompraDao.save(detallCompra);
            });
            DBConnectionImpl.getInstance().getConnection().commit();
            DBConnectionImpl.getInstance().getConnection().setAutoCommit(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                DBConnectionImpl.getInstance().getConnection().rollback();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        String resum = generaResumEmail(client);
        EnviadorEmail.enviaEmail(client.getEmail(), "Confirmación de compra: " + client.getCompra().getId_transaccio(), resum);
        PantallaConfirmacioCompraView.getInstance().confirmacioCompra(client);
    }

    private String generaResumEmail(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append("<body style=\"background-color: #2286c3\">");
        sb.append("<div style='flex-basis: 100%;\n" +
                "        display: flex;\n" +
                "        justify-content: center;\n" +
                "        align-content: center;\n" +
                "        align-items: center;'>\n" +
                "    <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/LIMBO_Logo.png/1200px-LIMBO_Logo.png\" width=\"300\" height=\"100\" alt=\"SharkApk\"/>\n" +
                "</div>");
        sb.append("<div style=\"margin: 20px auto; border-radius: 20px; height: 100vh;background-color: #e0e0e0; width: 50%; display: flex; flex-direction: column; justify-content: flex-start; align-content: space-around;\">");
        sb.append("<div style=\"display: flex; border-top-right-radius: 20px; border-top-left-radius: 20px; flex-direction: row; justify-content: center; background-color: #64b5f6\">");
        sb.append("<h1>Confirmación de la compra: " + client.getCompra().getId_transaccio() + "</h1>");
        sb.append("</div>");
        sb.append("<div style=\"padding:10px\">");
        sb.append("<h2>Hola " + client.getNom() + ", </h2>");
        sb.append("<p>Aquí tienes el resumen de la compra que has realizado hace tan sólo unos instantes en nuestra tienda Limbo:</p>");
        sb.append("<ul>\n" +
                "    <li><strong>Compra realizada el </strong> " + client.getCompra().getData() + "</li>" +
                "    <li><strong>Cliente:</strong> " + client.getNom() + " " + client.getCognom1() + " " + client.getCognom2() + "</li>" +
                "    <li><strong>Email:</strong> " + client.getEmail() + "</li>" +
                "    <li><strong>Envío:</strong> " + client.getCompra().getAdreca().getCarrer() + " " + client.getCompra().getAdreca().getNumero() + " " + client.getCompra().getAdreca().getCp() + "</li>" +
                "    <li><strong>Tarjeta usada:</strong> " + client.getCompra().getTargeta().getNumero() +  " " + client.getCompra().getTargeta().getTipus() +  " " + client.getCompra().getTargeta().getData_caducitat() + "</li>" +
                "  </ul>");
        sb.append("<h3>Productos incluidos en tu compra </h3>");
        sb.append("<ul>");
        client.getCompra().getProductes().forEach(detallCompra -> {
            sb.append("<li>"+ "<strong>" + detallCompra.getProducte().getNom() + "</strong>" + ": " + detallCompra.getUnitats_producte() + " " + detallCompra.getPvp() + "€" + "</li>");
        });
        sb.append("</ul>");
        sb.append("<p><strong>TOTAL: </strong>" + client.getCompra().getTotal() + "€" + "<p>");
        sb.append("<p>Muchas gracias por seguir confiando en <strong>Limbo</strong></p>");
        sb.append("<p>¡Hasta la próxima!</p>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</body>");
        return sb.toString();
    }

    public void goHome(Client client) {
        Compra newCompra = new Compra();
        newCompra.setClient(client);
        client.setCompra(newCompra);
        PantallaPrincipalController.getInstance().init(client);
    }

    public synchronized static PantallaConfirmacioCompraController getInstance() {
        if (instance == null) {
            instance = new PantallaConfirmacioCompraController();
        }
        return instance;
    }
}
