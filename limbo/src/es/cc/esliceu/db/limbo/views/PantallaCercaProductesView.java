package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaCercaProductesController;
import es.cc.esliceu.db.limbo.model.Categoria;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Producte;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;
import es.cc.esliceu.db.limbo.util.Notifications;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PantallaCercaProductesView {

    private static PantallaCercaProductesView instance;
    private final PantallaCercaProductesController controller;

    private PantallaCercaProductesView () {
        this.controller = PantallaCercaProductesController.getInstance();
    }

    public void init(Client client, Collection<Categoria> categories) {
        this.printHeader(client);
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "x) Sortir:" + Color.RESET);
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Nom producte:" + Color.RESET);
        String nom = MyScan.getInstance().getScanner().nextLine();
        if (nom.equals("x")) {
            this.controller.goBack(client);
        }
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Descripcio:" + Color.RESET);
        String descripcio = MyScan.getInstance().getScanner().nextLine();
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Marca:" + Color.RESET);
        String marca = MyScan.getInstance().getScanner().nextLine();
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Categoria:" + Color.RESET);
        AtomicInteger idx = new AtomicInteger();
        categories.forEach(categoria -> {
            System.out.println("      (" + Color.YELLOW_BRIGHT + "" + idx + Color.RESET + ") " + categoria.getNom() + " - " + categoria.getDescripcio());
            idx.set(idx.get() + 1);
        });
        String categoria = MyScan.getInstance().getScanner().nextLine();
        this.controller.searchProducts(client, nom, descripcio, marca, categoria);
    }

    public void resultProducts(Client client, Collection<Producte> productes) {
        System.out.println(Color.BLUE_BOLD + "S'han trobat " + productes.size() + " productes" + Color.RESET);
        this.printProductes(productes);
        boolean finished = false;
        while (!finished) {
            System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "f) Finalitzar: " + Color.RESET);
            System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "c) Tornar a cercar: " + Color.RESET);
            System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "x) Enrera: " + Color.RESET);
            System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Selecciona un producte: " + Color.RESET);
            String option = MyScan.getInstance().getScanner().nextLine();
            while (!option.equals("f") && !option.equals("c") && !option.equals("x")) {
                if (!option.equals("") && option.matches("\\d*") && Integer.parseInt(option) < productes.size()) {
                    break;
                }
                Notifications.errada("L'index introduir no es vàlid. Per favor, torna a intentar-ho");
                System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "f) Finalitzar: " + Color.RESET);
                System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "c) Tornar a cercar: " + Color.RESET);
                System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "x) Enrera: " + Color.RESET);
                System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Selecciona un producte: " + Color.RESET);
                option = MyScan.getInstance().getScanner().nextLine();
            }
            if (option.equals("f") || option.equals("c") || option.equals("x")) {
                this.controller.nextAction(client, option);
                return;
            }
            Producte producte = (Producte) productes.toArray()[Integer.parseInt(option)];
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Unitats: " + Color.RESET);
            String unitats = MyScan.getInstance().getScanner().nextLine();
            while (!unitats.matches("\\d*") || unitats.equals("0")) {
                Notifications.errada("La cantidad introduida no es vàlida. Torna a intentar-ho");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Unitats: " + Color.RESET);
                unitats = MyScan.getInstance().getScanner().nextLine().toLowerCase();
            }
            this.controller.addToCistella(client, producte, Integer.parseInt(unitats));
            System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Vols afegir més productes? : SI (s) | NO (n)" + Color.RESET);
            String more = MyScan.getInstance().getScanner().nextLine();
            if (more.equals("n")) finished = true;
            else {
                this.printHeader(client);
                this.printProductes(productes);
            }
        }
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Vols veure la cistella? : SI (s) | NO (n)" + Color.RESET);
        String showCistella = MyScan.getInstance().getScanner().nextLine();
        if (showCistella.equals("s")) this.controller.goToCistella(client);
        else this.controller.init(client);
    }

    private void printHeader(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**               Cerca                 **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.BLUE + "Cistella: " + Color.RED + client.getCompra().getTotal());
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
    }

    private void printProductes(Collection<Producte> productes) {
        AtomicInteger idx = new AtomicInteger();
        productes.forEach(producte -> {
            System.out.println(Color.YELLOW_BRIGHT + "" + idx + Color.RESET + "   " + producte.getDescripcio() + "   " + Color.CYAN_BRIGHT +producte.getNom() + "  " + Color.BLUE_BOLD + producte.getPvp() + "€");
            idx.set(idx.get() + 1);
        });
    }

    public synchronized static PantallaCercaProductesView getInstance() {
        if (instance == null) {
            instance = new PantallaCercaProductesView();
        }
        return instance;
    }
}
