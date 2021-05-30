package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaClientController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;
import es.cc.esliceu.db.limbo.util.Notifications;

import java.util.concurrent.atomic.AtomicInteger;

public class PantallaClientView {

    private static PantallaClientView instance;
    private final PantallaClientController controller;

    private PantallaClientView () {
        this.controller = PantallaClientController.getInstance();
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
        String option = MyScan.getInstance().getScanner().nextLine().toLowerCase();
        while (!option.equals("d") && !option.equals("c") && !option.equals("a") && !option.equals("t") && !option.equals("x")) {
            Notifications.errada("La opció escollida no es vàlida. Per favor, torna a intentar-ho");
            System.out.print(Color.YELLOW_BACKGROUND + "Esculli una opció: " + Color.RESET);
            option = MyScan.getInstance().getScanner().nextLine().toLowerCase();
        }
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
                idx.set(idx.get()+1);
            });
        } else {
            System.out.println(Color.YELLOW_BRIGHT + "No s'ha trobat cap adreça registrada al sistema." + Color.RESET);
        }
        if (client.getAdreces().size() < 3) {
            System.out.println("a) Afegir adreça d'enviament");
        }
        if (client.getAdreces().size() > 0) {
            System.out.println("b) Esborrar Adreça");
        }
        System.out.println("x) Sortir");
        System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Esculli una opció:" + Color.RESET + " ");
        String option = MyScan.getInstance().getScanner().nextLine().toLowerCase();
        while (!option.equals("a") && !option.equals("x") && !option.equals("b")) {
            Notifications.errada("La opció escollida no es vàlida. Torna a intentar-ho.");
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Esculli una opció:" + Color.RESET + " ");
            option = MyScan.getInstance().getScanner().nextLine().toLowerCase();
        }
        if (option.equals("b")) {
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Indiqui la posició de l'adreça que vol esborrar: " + Color.RESET + " ");
            String indexAdress = MyScan.getInstance().getScanner().nextLine();
            while (!indexAdress.matches("\\d*") && Integer.parseInt(indexAdress) > client.getAdreces().size()) {
                Notifications.errada("L'index introduit no és vàlid. Torna a intentar-ho");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Indiqui la posició de l'adreça que vol esborrar: " + Color.RESET + " ");
                indexAdress = MyScan.getInstance().getScanner().nextLine();
            }
            this.controller.deleteAdress(client, Integer.parseInt(indexAdress));
        }

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
                idx.set(idx.get()+1);
            });
        } else {
            System.out.println(Color.YELLOW_BRIGHT + "No s'ha trobat cap trageta registrada al sistema." + Color.RESET);
        }
        if (client.getTargetes().size() < 3) {
            System.out.println("a) Afegir targeta");
        }
        if (client.getTargetes().size() > 0) {
            System.out.println("b) Esborrar targeta");
        }
        System.out.println("x) Sortir");
        System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Esculli una opció:" + Color.RESET + " ");
        String option = MyScan.getInstance().getScanner().nextLine().toLowerCase();
        while (!option.equals("a") && !option.equals("x") && !option.equals("b")) {
            Notifications.errada("La opció escollida no es vàlida. Torna a intentar-ho.");
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Esculli una opció:" + Color.RESET + " ");
            option = MyScan.getInstance().getScanner().nextLine().toLowerCase();
        }
        if (option.equals("b")) {
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Indiqui la posició de la targeta que vol esborrar: " + Color.RESET + " ");
            String indexTargeta = MyScan.getInstance().getScanner().nextLine();
            while (!indexTargeta.matches("\\d*") && Integer.parseInt(indexTargeta) > client.getTargetes().size()) {
                Notifications.errada("L'index introduit no es un número vàlid. Torna a intentar-ho");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Indiqui la posició de la targeta que vol esborrar: " + Color.RESET + " ");
                indexTargeta = MyScan.getInstance().getScanner().nextLine();
            }
            this.controller.deleteTargeta(client, Integer.parseInt(indexTargeta));
        }
        this.controller.nextTargetesAction(client, option);
    }

    public synchronized static PantallaClientView getInstance() {
        if (instance == null) {
            instance = new PantallaClientView();
        }
        return instance;
    }

}
