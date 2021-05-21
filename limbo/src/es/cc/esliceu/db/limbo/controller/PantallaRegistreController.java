package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.EnviadorEmail;
import es.cc.esliceu.db.limbo.GeneradorHash;
import es.cc.esliceu.db.limbo.dao.ClientDao;
import es.cc.esliceu.db.limbo.dao.impl.ClientDaoImpl;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.views.PantallaRegistreView;

public class PantallaRegistreController {

    private final ClientDao clientDao;

    public PantallaRegistreController() {
        this.clientDao = new ClientDaoImpl(DBConnectionImpl.getInstance());
    }

    public void init() {
        PantallaRegistreView pantallaRegistreView = new PantallaRegistreView();
        pantallaRegistreView.init();
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
        EnviadorEmail.enviaEmail(email, "Codi de Referencia", " Aquí tens el codi de referencia: " + referenciaGenerada);
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

    public void registerSuccessful() {
        PantallaInicialController pantallaInicialController = new PantallaInicialController();
        pantallaInicialController.init();
    }
}
