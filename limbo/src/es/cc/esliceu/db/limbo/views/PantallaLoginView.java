package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaLoginController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;
import es.cc.esliceu.db.limbo.util.Notifications;

public class PantallaLoginView {

    private static PantallaLoginView instance;
    private final PantallaLoginController controller;

    private PantallaLoginView() {
        this.controller = PantallaLoginController.getInstance();
    }

    public void init() {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**                Login                **");
        System.out.println("*****************************************" + Color.RESET);
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "\t" + "Premi (x) per cancelar login..." + Color.RESET + " ");
        System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Username: " + Color.RESET + " ");
        String username = MyScan.getInstance().getScanner().nextLine();
        if (username.toLowerCase().equals("x")) this.controller.goBack();
        Client client = this.controller.checkIfExistsUsername(username);
        while (client == null || username.length() == 0) {
            Notifications.errada("Error: username no existeix. Provi de vell nou");
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Username: " + Color.RESET + " ");
            username = MyScan.getInstance().getScanner().nextLine();
            client = this.controller.checkIfExistsUsername(username);
        }
        Notifications.info("Usuari trobat al sistema");
        System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Password: " + Color.RESET + " ");
        String password = MyScan.getInstance().getScanner().nextLine();
        if (password.toLowerCase().equals("x")) this.controller.goBack();
        Integer attempts = 1;
        Integer totalAttemps = 3;
        while (!this.controller.checkIfPasswordsMatches(client, password)) {
            if (attempts >= 3) {
                Notifications.errada("Error de seguretat: S'ha superat els intents per introduir la contrasenya");
                System.exit(0);
            }
            Notifications.errada("Error: les contrasenya es incorrecte. Provi de vell nou");
            Integer attemptsLeft = totalAttemps - attempts;
            Notifications.info("Et queden " + attemptsLeft + " intents");
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Password: " + Color.RESET + " ");
            password = MyScan.getInstance().getScanner().nextLine();
            attempts++;
        }
        Notifications.info("LOGIN COMPLET!");
        this.controller.loginSuccessful(client);
    }


    public synchronized static PantallaLoginView getInstance() {
        if (instance == null) {
            instance = new PantallaLoginView();
        }
        return instance;
    }

}
