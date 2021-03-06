package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.EnviadorEmail;
import es.cc.esliceu.db.limbo.GeneradorHash;
import es.cc.esliceu.db.limbo.controller.PantallaSettingsController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;
import es.cc.esliceu.db.limbo.util.Notifications;

public class PantallaSettingsView {

    private static PantallaSettingsView instance;
    private final PantallaSettingsController controller;

    private PantallaSettingsView () {
        this.controller = PantallaSettingsController.getInstance();
    }

    public void init(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**              Settings               **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        System.out.println(Color.BLUE + "a) Modifica Dades Personals" + Color.RESET);
        System.out.println(Color.BLUE + "b) Canvi password" + Color.RESET);
        System.out.println(Color.BLUE + "x) Sortir" + Color.RESET);
        System.out.print(Color.YELLOW_BACKGROUND + "Esculli una opció: " + Color.RESET);
        String option = MyScan.getInstance().getScanner().nextLine().toLowerCase();
        while (!option.equals("a") && !option.equals("b") && !option.equals("x")) {
            Notifications.errada("La opció escollida no es vàlida. Torna a intentar-ho");
            System.out.print(Color.YELLOW_BACKGROUND + "Esculli una opció: " + Color.RESET);
            option = MyScan.getInstance().getScanner().nextLine().toLowerCase();
        }
        this.controller.nextAction(client, option);
    }

    public void modifyClient(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**         Modificar Informació        **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Nom: " + Color.CYAN + "( " + client.getNom() + " )");
        String nom = MyScan.getInstance().getScanner().nextLine();
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Cognom 1: " + Color.CYAN + "( " + client.getCognom1() + " )");
        String cognom1 = MyScan.getInstance().getScanner().nextLine();
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Cognom 2: " + Color.CYAN + "( " + client.getCognom2() + " )");
        String cognom2 = MyScan.getInstance().getScanner().nextLine();
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Email: " + Color.CYAN + "( " + client.getEmail() + " )");
        String email = MyScan.getInstance().getScanner().nextLine();
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Confirma les dades: " + Color.RESET);
        System.out.println("Nou nom: " + (nom.length() > 0 ? nom:client.getNom()));
        System.out.println("Nou cognom1: " + (cognom1.length() > 0 ? cognom1:client.getCognom1()));
        System.out.println("Nou cognom2: " + (cognom2.length() > 0 ? cognom2:client.getCognom2()));
        System.out.println("Nou email: " + (email.length() > 0 ? email:client.getEmail()));
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Voleu desar les dades? Si(s)/NO(n): " + Color.RESET);
        String option = MyScan.getInstance().getScanner().nextLine().toLowerCase();
        if (option.equals("s")) {
            client.setNom((nom.length() > 0 ? nom:client.getNom()));
            client.setCognom1((cognom1.length() > 0 ? cognom1:client.getCognom1()));
            client.setCognom2((cognom2.length() > 0 ? cognom2:client.getCognom2()));
            client.setEmail((email.length() > 0 ? email:client.getEmail()));
            this.controller.modifyClient(client);
        }
        this.controller.init(client);
    }

    public void changePassword(Client client) {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**           Modificar Password          **");
        System.out.println("Usuari: " + Color.RESET + client.getNom() + " " + client.getCognom1() + "    " + Color.RED_BRIGHT + client.getUsername() + Color.RESET);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Password Antic:" + Color.RESET);
        String oldPassword = MyScan.getInstance().getScanner().nextLine();
        while (!this.controller.checkOldPassword(client, oldPassword)) {
            Notifications.errada("La password antigua no coincideix. Prova-ho de vell nou.");
            oldPassword = MyScan.getInstance().getScanner().nextLine();
        }
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Password nou:" + Color.RESET);
        String newPassword = MyScan.getInstance().getScanner().nextLine();
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Repeteix el Password nou:" + Color.RESET);
        while (!newPassword.equals(MyScan.getInstance().getScanner().nextLine())) {
            Notifications.errada("Ops! Les contrasenyes no coincideixen. Repeteix el Password nou:");
        }
        String referencia = GeneradorHash.generaRandomString();
        EnviadorEmail.enviaEmail(client.getEmail(), "Confirmació canvi de contrasenya", "Aquí tens el codi de referencia per canviar la contrasenya: " + referencia);
        Notifications.info("S'ha enviat un correo electrónic amb un número de referència. Revisa la safata d'entrada.");
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Introdueix el codi de referència enviat al correu:" + Color.RESET);
        while (!referencia.equals(MyScan.getInstance().getScanner().nextLine())) {
            Notifications.errada("El codi de referència no coincideix... per favor, torna a intentar-ho.");
        }
    }

    public synchronized static PantallaSettingsView getInstance() {
        if (instance == null) {
            instance = new PantallaSettingsView();
        }
        return instance;
    }
}
