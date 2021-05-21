package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaCercaProductesController;
import es.cc.esliceu.db.limbo.model.Categoria;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Producte;
import es.cc.esliceu.db.limbo.util.Color;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PantallaCercaProductesView {

    private final PantallaCercaProductesController controller;

    public PantallaCercaProductesView () {
        this.controller = new PantallaCercaProductesController();
    }

    public void init(Client client, Collection<Categoria> categories) {
        this.printHeader(client);
        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "x) Sortir:" + Color.RESET);
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Nom producte:" + Color.RESET);
        String nom = scanner.nextLine();
        if (nom.equals("x")) {
            this.controller.goBack(client);
        }
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Descripcio:" + Color.RESET);
        String descripcio = scanner.nextLine();
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Marca:" + Color.RESET);
        String marca = scanner.nextLine();
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Categoria:" + Color.RESET);
        AtomicInteger idx = new AtomicInteger();
        categories.forEach(categoria -> {
            System.out.println("      (" + Color.YELLOW_BRIGHT + "" + idx + Color.RESET + ") " + categoria.getNom() + " - " + categoria.getDescripcio());
            idx.set(idx.get() + 1);
        });
        String categoria = scanner.nextLine();
        this.controller.searchProducts(client, nom, descripcio, marca, categoria);
    }

    public void resultProducts(Client client, Collection<Producte> productes) {
        System.out.println(Color.BLUE_BOLD + "S'han trobat " + productes.size() + " productes" + Color.RESET);
        this.printProductes(productes);
        Scanner scanner = new Scanner(System.in);
        boolean finished = false;
        while (!finished) {
            System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "f) Finalitzar: " + Color.RESET);
            System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "c) Tornar a cercar: " + Color.RESET);
            System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "x) Enrera: " + Color.RESET);
            System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Selecciona un producte: " + Color.RESET);
            String option = scanner.nextLine();
            if (option.equals("f") || option.equals("c") || option.equals("x")) {
                this.controller.nextAction(client, option);
                return;
            }
            Producte producte = (Producte) productes.toArray()[Integer.parseInt(option)];
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Unitats: " + Color.RESET);
            Integer unitats = scanner.nextInt();
            this.controller.addToCistella(client, producte, unitats);
            scanner.nextLine();
            System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Vols afegir més productes? : SI (s) | NO (n)" + Color.RESET);
            String more = scanner.nextLine();
            if (more.equals("n")) finished = true;
            else {
                this.printHeader(client);
                this.printProductes(productes);
            }
        }
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Vols veure la cistella? : SI (s) | NO (n)" + Color.RESET);
        String showCistella = scanner.nextLine();
        if (showCistella.equals("s")) this.controller.goToCistella(client);
        else this.controller.init(client);
    }

    private void printHeader(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**               Cerca                 **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.BLUE + "Cistella: " + Color.RED + client.getCistella().getTotal());
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
    }

    private void printProductes(Collection<Producte> productes) {
        AtomicInteger idx = new AtomicInteger();
        productes.forEach(producte -> {
            System.out.println(Color.YELLOW_BRIGHT + "" + idx + Color.RESET + "   " + producte.getDescripcio() + "   " + Color.CYAN_BRIGHT +producte.getNom() + "  " + Color.BLUE_BOLD + producte.getPvp() + "€");
            idx.set(idx.get() + 1);
        });
    }
}
