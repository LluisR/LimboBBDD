package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaConfirmacioCompraController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.Notifications;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class PantallaConfirmacioCompraView {

    private static PantallaConfirmacioCompraView instance;
    private final PantallaConfirmacioCompraController controller;

    private PantallaConfirmacioCompraView () {
        this.controller = new PantallaConfirmacioCompraController();
    }

    public void init(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**        Confirmació de Compra        **");
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        System.out.println("Es farà la següent compra amb la informació següent: ");
        System.out.println(client.getNom() + " " + client.getCognom1() + " " + client.getCognom2() + "  " + client.getEmail());
        AtomicInteger idx = new AtomicInteger();
        client.getCompra().getProductes().forEach(detallCompra -> {
            System.out.println(Color.YELLOW_BRIGHT + "" + idx + Color.BLUE_BRIGHT + "   " + detallCompra.getProducte().getNom() + Color.RESET +  "  " + detallCompra.getProducte().getPvp() + "€" + "  " + detallCompra.getUnitats_producte() + " unitats");
            idx.set(idx.get() + 1);
        });
        System.out.println("-------------------------------------------");
        System.out.println("Total cistella: " + Color.BLUE_BRIGHT + client.getCompra().getTotal() + "€" + Color.RESET);
        System.out.println("-------------------------------------------");
        System.out.println("Amb la targeta:");
        System.out.println(Color.CYAN_BOLD + "     " + client.getCompra().getTargeta().getNumero() + " " + client.getCompra().getTargeta().getTipus() + " " + client.getCompra().getTargeta().getData_caducitat() + Color.RESET);
        System.out.println("Amb la adreça d'enviament:");
        System.out.println(Color.CYAN_BOLD + "     " + client.getCompra().getAdreca().getCarrer() + " " + client.getCompra().getAdreca().getNumero() + " " + client.getCompra().getAdreca().getCp() + Color.RESET);
        System.out.println(Color.YELLOW_BACKGROUND + "Desitja continuar amb el pagament (s/n)?" +Color.RESET);
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine().toLowerCase();
        while (!option.equals("s") && !option.equals("n")) {
            Notifications.errada("Opció no vàlida. Torna a intentar-ho");
            option = scanner.nextLine().toLowerCase();
        }
        this.controller.nextAction(client, option);
    }

    public void confirmacioCompra(Client client) {
        Notifications.info("COMPRA REALITZADA CORRECTAMENT. S'ha enviat la factura a " + client.getEmail() + ". " + "Amb ID de transacció: " + client.getCompra().getId_transaccio());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pulsi qualsevol botó per continuar");
        scanner.nextLine();
        this.controller.goHome(client);
    }

    public synchronized static PantallaConfirmacioCompraView getInstance() {
        if (instance == null) {
            instance = new PantallaConfirmacioCompraView();
        }
        return instance;
    }
}
