package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.EnviadorEmail;
import es.cc.esliceu.db.limbo.GeneradorHash;
import es.cc.esliceu.db.limbo.dao.ClientDao;
import es.cc.esliceu.db.limbo.dao.impl.ClientDaoImpl;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.views.PantallaInicialView;
import es.cc.esliceu.db.limbo.views.PantallaRegistreView;

public class PantallaRegistreController {

    private static PantallaRegistreController instance;
    private final ClientDao clientDao;

    private PantallaRegistreController() {
        this.clientDao = ClientDaoImpl.getInstance();
    }

    public void init() {
        PantallaRegistreView.getInstance().init();
    }

    public boolean checkIfExistsUsername(String username) {
        Client client = this.clientDao.findByUserName(username);
        return client != null;
    }

    public boolean checkIfExistsEmail(String email) {
        Client client = this.clientDao.findByEmail(email);
        return client != null;
    }

    public String generateReference() {
        return GeneradorHash.generaRandomString();
    }

    public void sendEmail(String email, String referenciaGenerada) {
        EnviadorEmail.enviaEmail(email, "Codi de Referencia", this.generaEmail(referenciaGenerada));
    }

    public boolean checkReference(String referencia, String referenciaGenerada) {
        return referencia.equals(referenciaGenerada);
    }

    public Long saveNewUser (String username, String email, String contrasenya, String nom, String cognom1, String cognom2) {
        try {
            Client client = new Client();
            client.setUsername(username);
            client.setEmail(email);
            String hashPassword = GeneradorHash.generaHash(contrasenya);
            client.setContrasenya(hashPassword);
            client.setNom(nom);
            client.setCognom1(cognom1);
            client.setCognom2(cognom2);
            Long id = this.clientDao.save(client);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void goBack() {
        PantallaInicialView.getInstance().init();
    }

    public void registerSuccessful() {
        PantallaInicialController.getInstance().init();
    }

    private String generaEmail(String referenciaGenerada) {
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
        sb.append("<h1>Codi de referència</h1>");
        sb.append("</div>");
        sb.append("<div style=\"padding:10px\">");
        sb.append("<h2>Hola,</h2>");
        sb.append("<p>Aquí tienes tu código de referència para confirmar el registro en Limbo: " + "<strong>" + referenciaGenerada + "</strong></p>");
        sb.append("<p>Muchas gracias por seguir confiando en <strong>Limbo</strong></p>");
        sb.append("<p>¡Hasta la próxima!</p>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</body>");
        return sb.toString();
    }



    public synchronized static PantallaRegistreController getInstance() {
        if (instance == null) {
            instance = new PantallaRegistreController();
        }
        return instance;
    }
}
