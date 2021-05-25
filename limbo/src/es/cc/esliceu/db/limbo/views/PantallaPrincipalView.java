package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaInicialController;
import es.cc.esliceu.db.limbo.controller.PantallaPrincipalController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Producte;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.Notifications;

import java.util.Collection;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class PantallaPrincipalView {

    private final PantallaPrincipalController controller;

    public PantallaPrincipalView () {
        this.controller = new PantallaPrincipalController();
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
                System.out.println(producte.getNom() + "  " + producte.getPvp() + "  " + Color.RED_UNDERLINED + descompte.getPercentatge() + "%" + Color.RESET + " caduca: " + descompte.getDataFi());
            });
        });
        System.out.println(Color.RED_BOLD + "---------------------------------------------------------" + Color.RESET);
        System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "\t" + "Esculli una opció:" + Color.RESET + " ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine().toLowerCase();
        while (!option.equals("c") && !option.equals("v") && !option.equals("d") && !option.equals("h") && !option.equals("x")) {
            Notifications.errada("No s'ha seleccionat una opció valida. Torna a intentar-ho!");
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "\t" + "Esculli una opció:" + Color.RESET + " ");
            option = scanner.nextLine();
        }
        this.controller.nextPage(option, client);
    }
}
