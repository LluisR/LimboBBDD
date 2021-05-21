package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaCompresRealitzadesController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class PantallaCompresRealitzadesView {

    private final PantallaCompresRealitzadesController controller;

    public PantallaCompresRealitzadesView () {
        this.controller = new PantallaCompresRealitzadesController();
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
                    System.out.println("    " + producte.getProducte().getNom() + "  " + producte.getPvp() + "€  " + producte.getUnitats_producte() + " unitats.");
                });
            });
        } else {
            System.out.println(Color.YELLOW_BRIGHT + "No s'ha trobat cap compra registrada al sistema." + Color.RESET);
        }

        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Pitja qualsevol tecla per sortir" + Color.RESET + " ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        this.controller.goBack(client);
    }
}
