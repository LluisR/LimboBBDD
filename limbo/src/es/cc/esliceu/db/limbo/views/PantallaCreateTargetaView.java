package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaCreateTargetaController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.Notifications;

import java.util.Scanner;

public class PantallaCreateTargetaView {

    private static PantallaCreateTargetaView instance;
    private final PantallaCreateTargetaController controller;

    private PantallaCreateTargetaView () {
        this.controller = new PantallaCreateTargetaController();
    }

    public void init(Client client, String from) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**             Nova Targeta            **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        Scanner scanner = new Scanner(System.in);
        String option = "n";
        String tipusInitial = null;
        String tipus = null;
        String numero = null;
        String dataCaducitat = null;
        String codiSeguretat = null;
        while (option.equals("n")) {
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Tipus:" + Color.RESET + " ");
            tipusInitial = scanner.nextLine();
            tipus = tipusInitial.substring(0,1).toUpperCase() + tipusInitial.substring(1).toLowerCase();
            while (!tipus.equals("Visa") && !tipus.equals("Mastercard") && !tipus.equals("Maestro")) {
                Notifications.errada("Tipus de targeta incorrecte. Opcions disponibles: VISA O MASTERCARD");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Tipus (VISA/MASTERCARD):" + Color.RESET + " ");
                tipusInitial = scanner.nextLine();
                tipus = tipusInitial.substring(0,1).toUpperCase() + tipusInitial.substring(1).toLowerCase();
            }
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Numero:" + Color.RESET + " ");
            numero = scanner.nextLine();
            while(!numero.matches("\\d*")){
                Notifications.errada("No s'ha introduit un número vàlid. Torna a intentar-ho...");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Numero:" + Color.RESET + " ");
                numero = scanner.nextLine();
            }
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Data de caducitat (YYYY-MM-DD):" + Color.RESET + " ");
            dataCaducitat = scanner.nextLine();
            while (!dataCaducitat.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                Notifications.errada("No s'ha introduit una data vàlida. Torna a intentar-ho... (YYYY-MM-DD)");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Data de caducitat (YYYY-MM-DD):" + Color.RESET + " ");
                dataCaducitat = scanner.nextLine();
            }
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Codi de seguretat (3 dígits): " + Color.RESET + " ");
            codiSeguretat = scanner.nextLine();
            while (!codiSeguretat.matches("^\\d{3}$")) {
                Notifications.errada("No s'ha introduit un codi vàlid. Torna a intentar-ho... (YYYY-MM-DD");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Codi de seguretat (3 dígits): " + Color.RESET + " ");
                codiSeguretat = scanner.nextLine();
            }
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
        this.controller.saveNewTargeta(client, tipus, Long.parseLong(numero), dataCaducitat, Integer.parseInt(codiSeguretat));
        Notifications.info("Targeta afegida correctament!");
        this.controller.nextAction(client, from);
    }

    public synchronized static PantallaCreateTargetaView getInstance() {
        if (instance == null) {
            instance = new PantallaCreateTargetaView();
        }
        return instance;
    }

}
