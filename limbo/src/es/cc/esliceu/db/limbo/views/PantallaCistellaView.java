package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaCistellaController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.DetallCompra;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;

import java.util.concurrent.atomic.AtomicInteger;

public class PantallaCistellaView {

    private static PantallaCistellaView instance;
    private final PantallaCistellaController controller;

    private PantallaCistellaView () {
        this.controller = PantallaCistellaController.getInstance();
    }

    public void init(Client client) {
        this.printCistella(client);
    }

    private void printCistella(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**             Cistella               **");
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        AtomicInteger idx = new AtomicInteger();
        client.getCompra().getProductes().forEach((detallCompra) -> {
            System.out.println(Color.YELLOW_BRIGHT + "" + idx + Color.BLUE_BRIGHT + "   " + detallCompra.getProducte().getNom() + Color.RESET +  "  " + (detallCompra.getPvp()<detallCompra.getProducte().getPvp()?(detallCompra.getPvp() + "€  " + Color.RED_UNDERLINED + detallCompra.getProducte().getPvp() + "€" + Color.RESET + " -" + detallCompra.getPercentatge() + "%"):(detallCompra.getProducte().getPvp()) + "€") + "  " + detallCompra.getUnitats_producte() + " unitats");
            idx.set(idx.get() + 1);
        });
        System.out.println("-----------------------------------------");
        System.out.println("Total Cistella: " + client.getCompra().getTotal() + " €");
        System.out.println("-----------------------------------------");
        System.out.println("e) Eliminar Producte");
        System.out.println("p) Pagar");
        System.out.println("x) Sortir Cistella");
        String option = MyScan.getInstance().getScanner().nextLine();
        this.controller.nextAction(client, option, idx);
    }

    public void deleteProducte(Client client, AtomicInteger idx) {
        System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Tria el número de producte: " + Color.RESET);
        Integer select = MyScan.getInstance().getScanner().nextInt();
        while (select >= idx.get()) {
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Número erróni. Tria el número de producte de vell nou: " + Color.RESET);
            select = MyScan.getInstance().getScanner().nextInt();
        }
        MyScan.getInstance().getScanner().nextLine();
        DetallCompra producte = (DetallCompra) client.getCompra().getProductes().toArray()[select];
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Quantes unitats: (<Enter> per eliminar-los tots)" + Color.RESET);
        String unitats = MyScan.getInstance().getScanner().nextLine();
        if (!unitats.equals("")) {
            while (Integer.parseInt(unitats) >= producte.getUnitats_producte()) {
                System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Quantitat errónea. Torna a escollir quantes unitats: (<Enter> per eliminar-los tots)" + Color.RESET);
                unitats = MyScan.getInstance().getScanner().nextLine();
            }
        }
        this.controller.dropProduct(client, (DetallCompra) client.getCompra().getProductes().toArray()[select], unitats);
    }

    public synchronized static PantallaCistellaView getInstance() {
        if (instance == null) {
            instance = new PantallaCistellaView();
        }
        return instance;
    }
}
