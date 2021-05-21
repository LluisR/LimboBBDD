package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaClientController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class PantallaClientView {

    private final PantallaClientController controller;

    public PantallaClientView () {
        this.controller = new PantallaClientController();
    }

    public void init(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**          Dades Personals            **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        System.out.println(Color.BLUE + "d) Settings" + Color.RESET);
        System.out.println(Color.BLUE + "c) Compres realitzades" + Color.RESET);
        System.out.println(Color.BLUE + "a) Adreces" + Color.RESET);
        System.out.println(Color.BLUE + "t) Targetes" + Color.RESET);
        System.out.println(Color.BLUE + "x) Sortir" + Color.RESET);
        System.out.print(Color.YELLOW_BACKGROUND + "Esculli una opció: " + Color.RESET);
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        this.controller.nextAction(client, option);
    }

    public void showAdress(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**                Adreces               **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        AtomicInteger idx = new AtomicInteger();
        if (client.getAdreces() != null) {
            client.getAdreces().forEach(adreca -> {
                System.out.println(Color.YELLOW_BRIGHT + "" + idx.get() + Color.CYAN_BRIGHT + "   " + adreca.getCarrer() + " " + adreca.getNumero() + " " + adreca.getCp() + Color.RESET);
            });
        } else {
            System.out.println(Color.YELLOW_BRIGHT + "No s'ha trobat cap adreça registrada al sistema." + Color.RESET);
        }
        if (client.getAdreces().size() < 3) {
            System.out.println("a) Afegir adreça d'enviament");
        }
        System.out.println("x) Sortir");
        System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Esculli una opció:" + Color.RESET + " ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        this.controller.nextAddressAction(client, option);
    }

    public void showTargetes(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**             Targetes               **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        AtomicInteger idx = new AtomicInteger();
        if (client.getTargetes() != null) {
            client.getTargetes().forEach(targeta -> {
                System.out.println(Color.YELLOW_BRIGHT + "" + idx.get() + Color.CYAN_BRIGHT + "   " + targeta.getNumero() + " " + targeta.getTipus() + " " + targeta.getData_caducitat() + Color.RESET);
            });
        } else {
            System.out.println(Color.YELLOW_BRIGHT + "No s'ha trobat cap trageta registrada al sistema." + Color.RESET);
        }
        if (client.getTargetes().size() < 3) {
            System.out.println("a) Afegir targeta");
        }
        System.out.println("x) Sortir");
        System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Esculli una opció:" + Color.RESET + " ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        this.controller.nextTargetesAction(client, option);
    }

}
