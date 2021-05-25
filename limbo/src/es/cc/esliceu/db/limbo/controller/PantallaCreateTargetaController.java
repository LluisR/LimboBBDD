package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.dao.TargetaDao;
import es.cc.esliceu.db.limbo.dao.impl.TargetaDaoImpl;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Targeta;
import es.cc.esliceu.db.limbo.model.TipusTargeta;
import es.cc.esliceu.db.limbo.views.PantallaCreateTargetaView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PantallaCreateTargetaController {

    private final TargetaDao targetaDao;

    public PantallaCreateTargetaController() {
        this.targetaDao = TargetaDaoImpl.getInstance();
    }

    public void init(Client client, String from) {
        PantallaCreateTargetaView.getInstance().init(client, from);
    }

    public void saveNewTargeta (Client client, String tipus, Long numero, String dataCaducitat, Integer codiSeguretat) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dataCaducitat);
        } catch (ParseException e) {
            System.out.println("ERRRR");
        }
        Targeta targeta = new Targeta();
        targeta.setClient(client);
        targeta.setTipus(TipusTargeta.valueOf(tipus));
        targeta.setNumero(numero);
        targeta.setData_caducitat(date);
        targeta.setCodi_seguretat(codiSeguretat);
        this.targetaDao.save(targeta);
        client.getTargetes().add(targeta);
    }

    public void nextAction(Client client, String from) {
        if (from.equals("settings")) {
            PantallaPrincipalController pantallaPrincipalController = new PantallaPrincipalController(); pantallaPrincipalController.init(client);
        } else if (from.equals("pagament")) {
            PantallaPagamentController pantallaPagamentController = new PantallaPagamentController(); pantallaPagamentController.init(client);
        }
    }
}