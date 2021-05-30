package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaEnviamentController;
import es.cc.esliceu.db.limbo.model.Adreca;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;
import es.cc.esliceu.db.limbo.util.Notifications;

import java.util.concurrent.atomic.AtomicInteger;

public class PantallaEnviamentView {

    private static PantallaEnviamentView instance;
    private final PantallaEnviamentController controller;

    private PantallaEnviamentView () {
        this.controller = PantallaEnviamentController.getInstance();
    }

    public void init(Client client) {
        printEnviament(client);
    }

    private void printEnviament(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**             Enviament               **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.BLUE + "Cistella: " + Color.RED + client.getCompra().getTotal());
        if (client.getCompra().getTargeta() != null) {
            System.out.println(Color.BLUE + "Targeta seleccionada: " + Color.RED + client.getCompra().getTargeta().getTipus() + "  " + client.getCompra().getTargeta().getNumero());
        }
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        System.out.println("Adreces d'enviament disponibles:");
        AtomicInteger idx = new AtomicInteger();;
        client.getAdreces().forEach(adreca -> {
            System.out.println(Color.YELLOW_BRIGHT + "" + idx + Color.BLUE_BRIGHT + "   " + adreca.getCarrer() + " " + adreca.getNumero() + " " + adreca.getCp() + Color.RESET);
            idx.set(idx.get() + 1);
        });
        if (client.getAdreces().size() < 3) {
            System.out.println("a) Afegir adreça d'enviament");
        }
        if (client.getAdreces().size() > 0) {
            System.out.println("b) Esborrar adreça");
        }
        System.out.println("x) Sortir");
        System.out.print(Color.YELLOW_BACKGROUND + "Esculli una opció: " + Color.RESET);
        String option = MyScan.getInstance().getScanner().nextLine();

        while (!option.equals("a") && !option.equals("b") && !option.equals("x")) {
            if (option.matches("\\d*")) {
                if (Integer.parseInt(option) < client.getAdreces().size()) {
                    break;
                }
            }
            Notifications.errada("No has seleccionat una opció valida. Torna a intentar-ho.");
            System.out.print(Color.YELLOW_BACKGROUND + "Esculli una opció: " + Color.RESET);
            option = MyScan.getInstance().getScanner().nextLine();
        }
        if (option.equals("b")) {
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Indiqui el número índex de l'adreça que vol esborrar: " + Color.RESET);
            String deleteAdress = MyScan.getInstance().getScanner().nextLine();
            while (!deleteAdress.matches("\\d*") || Integer.parseInt(deleteAdress) >= client.getAdreces().size()) {
                Notifications.errada("El número introduit no es vàlid. Torna a intentar-ho");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Indiqui el número índex de l'adreça que vol esborrar: " + Color.RESET);
                deleteAdress = MyScan.getInstance().getScanner().nextLine();
            }
            this.controller.deleteAdress((Adreca)client.getAdreces().toArray()[Integer.parseInt(deleteAdress)]);
        }
        this.controller.nextAction(client, option);
    }

    public synchronized static PantallaEnviamentView getInstance() {
        if (instance == null) {
            instance = new PantallaEnviamentView();
        }
        return instance;
    }
}
