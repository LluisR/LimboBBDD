package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaPagamentController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class PantallaPagamentView {

    private final PantallaPagamentController controller;

    public PantallaPagamentView () {
        this.controller = new PantallaPagamentController();
    }

    public void init(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**             Pagament               **");
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        AtomicInteger idx = new AtomicInteger();
        client.getCistella().getProductes().forEach(detallCompra -> {
            System.out.println(Color.YELLOW_BRIGHT + "" + idx + Color.BLUE_BRIGHT + "   " + detallCompra.getProducte().getNom() + Color.RESET +  "  " + detallCompra.getProducte().getPvp() + "€" + "  " + detallCompra.getUnitats_producte() + " unitats");
            idx.set(idx.get() + 1);
        });
        System.out.println("-----------------------------------------");
        System.out.println("Total Cistella: " + client.getCistella().getTotal() + " €");
        System.out.println("-----------------------------------------");
        System.out.println("Targetes disponibles:");
        AtomicInteger idx2 = new AtomicInteger();
        client.getTargetes().forEach(targeta -> {
            System.out.println(Color.YELLOW_BRIGHT + "" + idx2 + Color.BLUE_BRIGHT + "  " + targeta.getNumero() +  " " + targeta.getTipus() + " " + targeta.getData_caducitat() + Color.RESET);
            idx2.set(idx2.get() + 1);
        });
        System.out.println("a) Afegir targeta");
        System.out.println("x) Sortir");
        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Esculli una opció" + Color.RESET);
        String option = scanner.nextLine();
        this.controller.nextAction(client, option);
    }
}
