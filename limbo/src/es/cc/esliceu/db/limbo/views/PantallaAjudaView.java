package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaAjudaController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;

import java.util.Scanner;

public class PantallaAjudaView {

    private final PantallaAjudaController controller;

    public PantallaAjudaView () {
        this.controller = new PantallaAjudaController();
    }

    public void init(Client client) {
        this.printHelp();
        this.controller.goBack(client);
    }

    private void printHelp() {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**               Ajuda                 **");
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        System.out.println("Premi qualsevol botó per tornar a enrere");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
