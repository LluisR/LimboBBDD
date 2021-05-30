package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaPagamentController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Targeta;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;
import es.cc.esliceu.db.limbo.util.Notifications;

import java.util.concurrent.atomic.AtomicInteger;

public class PantallaPagamentView {

    private static PantallaPagamentView instance;
    private final PantallaPagamentController controller;

    private PantallaPagamentView () {
        this.controller = PantallaPagamentController.getInstance();
    }

    public void init(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**             Pagament               **");
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        AtomicInteger idx = new AtomicInteger();
        client.getCompra().getProductes().forEach(detallCompra -> {
            System.out.println(Color.YELLOW_BRIGHT + "" + idx + Color.BLUE_BRIGHT + "   " + detallCompra.getProducte().getNom() + Color.RESET +  "  " + detallCompra.getProducte().getPvp() + "€" + "  " + detallCompra.getUnitats_producte() + " unitats");
            idx.set(idx.get() + 1);
        });
        System.out.println("-----------------------------------------");
        System.out.println("Total Cistella: " + client.getCompra().getTotal() + " €");
        System.out.println("-----------------------------------------");
        System.out.println("Targetes disponibles:");
        AtomicInteger idx2 = new AtomicInteger();
        if (client.getTargetes().size() > 0) {
            client.getTargetes().forEach(targeta -> {
                System.out.println(Color.YELLOW_BRIGHT + "" + idx2 + Color.BLUE_BRIGHT + "  " + targeta.getNumero() +  " " + targeta.getTipus() + " " + targeta.getData_caducitat() + Color.RESET);
                idx2.set(idx2.get() + 1);
            });
        } else {
            System.out.println("No s'ha trobat cap targeta. Pots afegir una nova targeta pitjant l'opció a)");
        }

        if (client.getTargetes().size() < 3) {
            System.out.println("a) Afegir targeta");
        }
        if (client.getTargetes().size() > 0) {
            System.out.println("b) Esborrar targeta");
        }
        System.out.println("x) Sortir");
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Esculli una opció" + Color.RESET);
        String option = MyScan.getInstance().getScanner().nextLine();
        while (!option.equals("a") && !option.equals("b") && !option.equals("x")) {
            if (option.matches("\\d*")) {
                if (Integer.parseInt(option) < client.getTargetes().size()) {
                    break;
                }
            }
            Notifications.errada("No has seleccionat una opció valida. Torna a intentar-ho.");
            System.out.print(Color.YELLOW_BACKGROUND + "Esculli una opció: " + Color.RESET);
            option = MyScan.getInstance().getScanner().nextLine();
        }
        if (option.equals("b")) {
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Indiqui el número índex de la targeta que vol esborrar: " + Color.RESET);
            String deleteTargeta = MyScan.getInstance().getScanner().nextLine();
            while (!deleteTargeta.matches("\\d*") || Integer.parseInt(deleteTargeta) >= client.getTargetes().size()) {
                Notifications.errada("El número introduit no es vàlid. Torna a intentar-ho");
                System.out.print(Color.YELLOW_BACKGROUND +  "" + Color.BLACK_BOLD + "Indiqui el número índex de la targeta que vol esborrar: " + Color.RESET);
                deleteTargeta = MyScan.getInstance().getScanner().nextLine();
            }
            this.controller.deleteTargeta((Targeta)client.getTargetes().toArray()[Integer.parseInt(deleteTargeta)]);
        }
        this.controller.nextAction(client, option);
    }

    public synchronized static PantallaPagamentView getInstance() {
        if (instance == null) {
            instance = new PantallaPagamentView();
        }
        return instance;
    }
}
