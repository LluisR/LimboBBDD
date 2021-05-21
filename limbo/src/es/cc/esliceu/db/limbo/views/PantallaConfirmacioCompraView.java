package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaConfirmacioCompraController;
import es.cc.esliceu.db.limbo.model.Adreca;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Compra;
import es.cc.esliceu.db.limbo.model.Targeta;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.Notifications;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class PantallaConfirmacioCompraView {

    private final PantallaConfirmacioCompraController controller;

    public PantallaConfirmacioCompraView () {
        this.controller = new PantallaConfirmacioCompraController();
    }

    public void init(Client client, Targeta targeta, Adreca adreca) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**        Confirmació de Compra        **");
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        System.out.println("Es farà la següent compra amb la informació següent: ");
        System.out.println(client.getNom() + " " + client.getCognom1() + " " + client.getCognom2() + "  " + client.getEmail());
        AtomicInteger idx = new AtomicInteger();
        client.getCistella().getProductes().forEach(detallCompra -> {
            System.out.println(Color.YELLOW_BRIGHT + "" + idx + Color.BLUE_BRIGHT + "   " + detallCompra.getProducte().getNom() + Color.RESET +  "  " + detallCompra.getProducte().getPvp() + "€" + "  " + detallCompra.getUnitats_producte() + " unitats");
            idx.set(idx.get() + 1);
        });
        System.out.println("-------------------------------------------");
        System.out.println("Total cistella: " + Color.BLUE_BRIGHT + client.getCistella().getTotal() + "€" + Color.RESET);
        System.out.println("-------------------------------------------");
        System.out.println("Amb la targeta:");
        System.out.println(Color.CYAN_BOLD + "     " + targeta.getNumero() + " " + targeta.getTipus() + " " + targeta.getData_caducitat() + Color.RESET);
        System.out.println("Amb la adreça d'enviament:");
        System.out.println(Color.CYAN_BOLD + "     " + adreca.getCarrer() + " " + adreca.getNumero() + " " + adreca.getCp() + Color.RESET);
        System.out.println(Color.YELLOW_BACKGROUND + "Desitja continuar amb el pagament (s/n)?" +Color.RESET);
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine().toLowerCase();
        while (!option.equals("s") && !option.equals("n")) {
            Notifications.errada("Opció no vàlida. Torna a intentar-ho");
            option = scanner.nextLine().toLowerCase();
        }
        this.controller.nextAction(client, targeta, adreca, option);
    }

    public void confirmacioCompra(Client client, Compra compra) {
        Notifications.info("COMPRA REALITZADA CORRECTAMENT. S'ha enviat la factura a " + client.getEmail() + ". " + "Amb ID de transacció: " + compra.getId_transaccio());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pulsi qualsevol botó per continuar");
        scanner.nextLine();
        this.controller.goHome(client);
    }
}
