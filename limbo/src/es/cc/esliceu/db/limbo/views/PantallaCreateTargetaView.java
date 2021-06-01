package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaCreateTargetaController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;
import es.cc.esliceu.db.limbo.util.Notifications;

public class PantallaCreateTargetaView {

    private static PantallaCreateTargetaView instance;
    private final PantallaCreateTargetaController controller;

    private PantallaCreateTargetaView () {
        this.controller = PantallaCreateTargetaController.getInstance();
    }

    public void init(Client client, String from) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**             Nova Targeta            **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        String option = "n";
        String tipusInitial = null;
        String tipus = null;
        String numero = null;
        String dataCaducitat = null;
        String mesCaducitat = null;
        String anyCaducitat = null;
        String codiSeguretat = null;
        while (option.equals("n") || option.equals("x")) {
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Tipus (VISA/MASTERCARD/MAESTRO): " + Color.RESET + " ");
            tipusInitial = MyScan.getInstance().getScanner().nextLine();
            tipus = tipusInitial.substring(0,1).toUpperCase() + tipusInitial.substring(1).toLowerCase();
            while (!tipus.equals("Visa") && !tipus.equals("Mastercard") && !tipus.equals("Maestro")) {
                Notifications.errada("Tipus de targeta incorrecte. Opcions disponibles: VISA O MASTERCARD");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Tipus (VISA/MASTERCARD/MAESTRO): " + Color.RESET + " ");
                tipusInitial = MyScan.getInstance().getScanner().nextLine();
                tipus = tipusInitial.substring(0,1).toUpperCase() + tipusInitial.substring(1).toLowerCase();
            }
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Numero:" + Color.RESET + " ");
            numero = MyScan.getInstance().getScanner().nextLine();
            while(!numero.matches("^\\d{16}$")){
                Notifications.errada("No s'ha introduit un número vàlid. Torna a intentar-ho... Recorda que han de ser 16 dígits");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Numero:" + Color.RESET + " ");
                numero = MyScan.getInstance().getScanner().nextLine();
            }
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Mes de caducitat" + Color.RESET + " ");
            mesCaducitat = MyScan.getInstance().getScanner().nextLine();
            while (!mesCaducitat.matches("^(0?[1-9]|1[012])$")) {
                Notifications.errada("El mes introduit no es vàlid. Torna a intentar-ho (1-12)");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Mes de caducitat (2 dígits): " + Color.RESET + " ");
                mesCaducitat = MyScan.getInstance().getScanner().nextLine();
            }
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Any de caducitat (2 dígits): " + Color.RESET + " ");
            anyCaducitat = MyScan.getInstance().getScanner().nextLine();
            while (!anyCaducitat.matches("^\\d{2}$")) {
                Notifications.errada("L'any introduit no es vàlid. Torna a intentar-ho (2 dígits)");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Mes de caducitat" + Color.RESET + " ");
                anyCaducitat = MyScan.getInstance().getScanner().nextLine();
            }
            dataCaducitat = "20" + anyCaducitat + "-" + mesCaducitat + "-" + "01";
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Codi de seguretat (3 dígits): " + Color.RESET + " ");
            codiSeguretat = MyScan.getInstance().getScanner().nextLine();
            while (!codiSeguretat.matches("^\\d{3}$")) {
                Notifications.errada("No s'ha introduit un codi vàlid. Torna a intentar-ho... (YYYY-MM-DD");
                System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Codi de seguretat (3 dígits): " + Color.RESET + " ");
                codiSeguretat = MyScan.getInstance().getScanner().nextLine();
            }
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "CP:" + Color.RESET + " ");
            System.out.println("--------------------------------------");
            System.out.println("Revisa les dades següents:");
            System.out.println("Tipus: " + tipus);
            System.out.println("Numero: " + numero);
            System.out.println("Data de caducitat: " + dataCaducitat);
            System.out.println("Codi de seguretat: " + codiSeguretat);
            System.out.println("--------------------------------------");
            System.out.println("Es correcte? Si(s)/No(n). Premi x per cancelar");
            option = MyScan.getInstance().getScanner().nextLine().toLowerCase();
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
