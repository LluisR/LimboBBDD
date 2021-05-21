package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaEnviamentController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Targeta;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.Notifications;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class PantallaEnviamentView {

    private final PantallaEnviamentController controller;

    public PantallaEnviamentView () {
        this.controller = new PantallaEnviamentController();
    }

    public void init(Client client, Targeta targeta) {
        printEnviament(client, targeta);
    }

    private void printEnviament(Client client, Targeta targeta) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**             Enviament               **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.BLUE + "Cistella: " + Color.RED + client.getCistella().getTotal());
        if (targeta != null) {
            System.out.println(Color.BLUE + "Targeta seleccionada: " + Color.RED + targeta.getTipus() + "  " + targeta.getNumero());
        }
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        System.out.println("Adreces d'enviament disponibles:");
        AtomicInteger idx = new AtomicInteger();;
        client.getAdreces().forEach(adreca -> {
            System.out.println(Color.YELLOW_BRIGHT + "" + idx + Color.BLUE_BRIGHT + "   " + adreca.getCarrer() + " " + adreca.getNumero() + " " + adreca.getCp() + Color.RESET);
            idx.set(idx.get() + 1);
        });
        System.out.println("a) Afegir adreça d'enviament");
        System.out.println("x) Sortir");
        System.out.print(Color.YELLOW_BACKGROUND + "Esculli una opció: " + Color.RESET);
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if (targeta != null) {
            if (!option.equals("")) {
                while (!option.equals("a") && !option.equals("x") && Integer.parseInt(option) >= client.getAdreces().size()) {
                    Notifications.errada("No has seleccionat una opció valida. Torna a intentar-ho.");
                    option = scanner.nextLine();
                }
            }
        } else {
            if (!option.equals("")) {
                while (!option.equals("a") && !option.equals("x")) {
                    Notifications.errada("No has seleccionat una opció valida. Torna a intentar-ho.");
                    option = scanner.nextLine();
                }
            }
        }
        this.controller.nextAction(client, targeta, option);
    }
}
