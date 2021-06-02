package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaPrincipalController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Producte;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;
import es.cc.esliceu.db.limbo.util.Notifications;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class PantallaPrincipalView {

    private static PantallaPrincipalView instance;
    private final PantallaPrincipalController controller;

    private PantallaPrincipalView () {
        this.controller = PantallaPrincipalController.getInstance();
    }

    public void init(Client client, Collection<Producte> productesSuggerits) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**             Opcions               **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        System.out.println(Color.BLUE + "c) Cerca productes" + Color.RESET);
        System.out.println(Color.BLUE + "v) Veure cistella" + Color.RESET);
        System.out.println(Color.BLUE + "d) Dades Personals" + Color.RESET);
        System.out.println(Color.BLUE + "h) Ajuda" + Color.RESET);
        System.out.println(Color.BLUE + "x) Sortir" + Color.RESET);
        System.out.println(Color.RED_BOLD + "---------------    Productes Suggerits    ---------------" + Color.RESET);
        AtomicInteger idx = new AtomicInteger();
        productesSuggerits.forEach(producte -> {
            System.out.println(Color.YELLOW_BRIGHT + "" + idx + "   " + Color.RESET + producte.getNom() + "  " + producte.getDescripcio() + "  " + Color.CYAN + producte.getMarca() + "  " + Color.BLUE_BOLD + producte.getPvp() + "€");
            idx.set(idx.get() + 1);
        });
        System.out.println(Color.RED_BOLD + "---------------------------------------------------------" + Color.RESET);
        System.out.println(Color.BLUE + "(0-" + (productesSuggerits.size()-1) + ")" + Color.RESET + " Producte suggerits");
        System.out.println(Color.RED_BOLD + "---------------    Descomptes Actius    ---------------" + Color.RESET);
        client.getDescomptes().forEach((descompte, productes) -> {
            productes.forEach(producte -> {
                System.out.println(producte.getNom() + "  " + producte.getPvp() + "€  " + Color.RED_UNDERLINED + descompte.getPercentatge() + "%" + Color.RESET + " caduca: " + descompte.getDataFi());
            });
        });
        System.out.println(Color.RED_BOLD + "---------------------------------------------------------" + Color.RESET);
        System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "\t" + "Esculli una opció:" + Color.RESET + " ");
        String option = MyScan.getInstance().getScanner().nextLine().toLowerCase();
        while (!option.matches("^([0-4]{1})?$") && !option.equals("c") && !option.equals("v") && !option.equals("d") && !option.equals("h") && !option.equals("x")) {
            Notifications.errada("No s'ha seleccionat una opció valida. Torna a intentar-ho!");
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "\t" + "Esculli una opció:" + Color.RESET + " ");
            option = MyScan.getInstance().getScanner().nextLine();
        }
        if (option.matches("^([0-4]{1})?$")) {
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "\t" + "Quantes unitats vols?: " + Color.RESET + " ");
            String unitats = MyScan.getInstance().getScanner().nextLine();
            while (!unitats.matches("^([0-9]+)$")) {
                Notifications.errada("Les unitats introduides no son vàlides. Torna a intentar-ho...");
                unitats = MyScan.getInstance().getScanner().nextLine();
            }
            this.controller.addToCistella(option, client, productesSuggerits, unitats);
        }
        this.controller.nextPage(option, client);
    }

    public synchronized static PantallaPrincipalView getInstance() {
        if (instance == null) {
            instance = new PantallaPrincipalView();
        }
        return instance;
    }

}
