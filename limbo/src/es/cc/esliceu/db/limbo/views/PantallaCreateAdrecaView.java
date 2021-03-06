package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaCreateAdrecaController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;
import es.cc.esliceu.db.limbo.util.Notifications;

public class PantallaCreateAdrecaView {

    private static PantallaCreateAdrecaView instance;
    private final PantallaCreateAdrecaController controller;

    private PantallaCreateAdrecaView () {
        this.controller = PantallaCreateAdrecaController.getInstance();
    }

    public void init(Client client, String from) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**              Nova Adreca             **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        String option = "n";
        String carrer = null;
        String numero = null;
        String pis = null;
        String porta = null;
        String cp = null;
        while (option.equals("n")) {
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Nom del carrer:" + Color.RESET + " ");
            carrer = MyScan.getInstance().getScanner().nextLine();
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Numero:" + Color.RESET + " ");
            numero = MyScan.getInstance().getScanner().nextLine();
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Pis:" + Color.RESET + " ");
            pis = MyScan.getInstance().getScanner().nextLine();
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Porta:" + Color.RESET + " ");
            porta = MyScan.getInstance().getScanner().nextLine();
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "CP:" + Color.RESET + " ");
            cp = MyScan.getInstance().getScanner().nextLine();
            System.out.println("--------------------------------------");
            System.out.println("Revisa les dades seg??ents:");
            System.out.println("Nom: " + carrer);
            System.out.println("Numero: " + numero);
            System.out.println("Pis: " + pis);
            System.out.println("Porta: " + porta);
            System.out.println("CP: " + cp);
            System.out.println("--------------------------------------");
            System.out.println("Es correcte? Si(s)/No(n)");
            option = MyScan.getInstance().getScanner().nextLine();
        }
        this.controller.saveNewAdreca(client, carrer, numero, Integer.parseInt(pis), porta, cp);
        Notifications.info("Adre??a afegida correctament!");
        this.controller.nextAction(client, from);
    }

    public synchronized static PantallaCreateAdrecaView getInstance() {
        if (instance == null) {
            instance = new PantallaCreateAdrecaView();
        }
        return instance;
    }
}
