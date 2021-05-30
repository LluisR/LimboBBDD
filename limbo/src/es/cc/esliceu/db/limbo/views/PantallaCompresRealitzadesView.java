package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaCompresRealitzadesController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;

import java.util.concurrent.atomic.AtomicInteger;

public class PantallaCompresRealitzadesView {

    private static PantallaCompresRealitzadesView instance;
    private final PantallaCompresRealitzadesController controller;

    private PantallaCompresRealitzadesView () {
        this.controller = PantallaCompresRealitzadesController.getInstance();
    }

    public void init(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**              Compres                **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        AtomicInteger idx = new AtomicInteger();
        if (client.getCompres().size()>0) {
            client.getCompres().forEach((compra) -> {
                System.out.println(Color.YELLOW_BRIGHT + "" + idx.get() + Color.CYAN_BRIGHT + "  " + compra.getData() + " " + compra.getId_transaccio() + " " + Color.RED_BOLD + compra.getTotal() + "€" + Color.RESET);
                compra.getProductes().forEach(producte -> {
                    System.out.println("     " + producte.getProducte().getNom() + "  " + (producte.getPvp()<producte.getProducte().getPvp()?(producte.getPvp() + "€  " + Color.RED_UNDERLINED + Math.round(100-(producte.getPvp()*100/(producte.getProducte().getPvp()))) + "%"+ Color.RESET + "  " + Color.RED_UNDERLINED + producte.getProducte().getPvp() + "€" + Color.RESET):(producte.getProducte().getPvp()) + "€") + "  " + producte.getUnitats_producte() + " unitats.");
                });
            });
        } else {
            System.out.println(Color.YELLOW_BRIGHT + "No s'ha trobat cap compra registrada al sistema." + Color.RESET);
        }

        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Pitja qualsevol tecla per sortir" + Color.RESET + " ");
        MyScan.getInstance().getScanner().nextLine();
        this.controller.goBack(client);
    }

    public synchronized static PantallaCompresRealitzadesView getInstance() {
        if (instance == null) {
            instance = new PantallaCompresRealitzadesView();
        }
        return instance;
    }
}
