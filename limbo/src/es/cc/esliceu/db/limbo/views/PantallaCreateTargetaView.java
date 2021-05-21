package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaCreateTargetaController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.Notifications;

import java.sql.Date;
import java.util.Scanner;

public class PantallaCreateTargetaView {

    private final PantallaCreateTargetaController controller;

    public PantallaCreateTargetaView () {
        this.controller = new PantallaCreateTargetaController();
    }

    public void init(Client client, String from) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**             Nova Targeta            **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        Scanner scanner = new Scanner(System.in);
        String option = "n";
        String tipus = null;
        String numero = null;
        String dataCaducitat = null;
        Integer codiSeguretat = null;
        while (option.equals("n")) {
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Tipus:" + Color.RESET + " ");
            tipus = scanner.nextLine();
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Numero:" + Color.RESET + " ");
            numero = scanner.nextLine();
            while(!numero.matches("\\d*")){
                Notifications.errada("No s'ha introduit un número vàlit. Torna a intentar-ho...");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Numero:" + Color.RESET + " ");
                numero = scanner.nextLine();
            }
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Data de caducitat (YYYY-MM-DD):" + Color.RESET + " ");
            dataCaducitat = scanner.nextLine();
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Codi de seguretat:" + Color.RESET + " ");
            codiSeguretat = scanner.nextInt();
            scanner.nextLine();
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "CP:" + Color.RESET + " ");
            System.out.println("--------------------------------------");
            System.out.println("Revisa les dades següents:");
            System.out.println("Tipus: " + tipus);
            System.out.println("Numero: " + numero);
            System.out.println("Data de caducitat: " + dataCaducitat);
            System.out.println("Codi de seguretat: " + codiSeguretat);
            System.out.println("--------------------------------------");
            System.out.println("Es correcte? Si(s)/No(n)");
            option = scanner.nextLine();
        }
        this.controller.saveNewTargeta(client, tipus, Long.parseLong(numero), dataCaducitat, codiSeguretat);
        Notifications.info("Targeta afegida correctament!");
        this.controller.nextAction(client, from);
    }

}
