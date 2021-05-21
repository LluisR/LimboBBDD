package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaCompresRealitzadesController;
import es.cc.esliceu.db.limbo.model.Cistella;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Compra;
import es.cc.esliceu.db.limbo.util.Color;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class PantallaCompresRealitzadesView {

    private final PantallaCompresRealitzadesController controller;

    public PantallaCompresRealitzadesView () {
        this.controller = new PantallaCompresRealitzadesController();
    }

    public void init(Client client, Map<Cistella, Compra> historialCompres) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**             Compres               **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        AtomicInteger idx = new AtomicInteger();
        if (historialCompres.size()>0) {
            historialCompres.forEach((cistella, compra) -> {
                System.out.println(Color.YELLOW_BRIGHT + "" + idx.get() + Color.CYAN_BRIGHT + "   " + compra.getData() + " " + compra.getId_transaccio() + " " + cistella.getTotal() + Color.RESET);
                cistella.getProductes().forEach(producte -> {
                    System.out.println("      " + producte.getProducte().getNom() + "  " + producte.getPvp() + "  " + producte.getUnitats_producte());
                });
            });
        } else {
            System.out.println(Color.YELLOW_BRIGHT + "No s'ha trobat cap compra registrada al sistema." + Color.RESET);
        }

        System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Pitja qualsevol tecla per sortir" + Color.RESET + " ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        this.controller.goBack(client);
    }
}
