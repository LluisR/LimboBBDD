package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaLoginController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.Notifications;

import java.util.Scanner;

public class PantallaLoginView {

    private final PantallaLoginController controller;

    public PantallaLoginView() {
        this.controller = new PantallaLoginController();
    }

    public void init() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**                Login                **");
        System.out.println("*****************************************" + Color.RESET);
        System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Username: " + Color.RESET + " ");
        String username = scanner.nextLine();
        Client client = this.controller.checkIfExistsUsername(username);
        while (client == null) {
            Notifications.errada("Error: username no existeix. Provi de vell nou");
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Username: " + Color.RESET + " ");
            username = scanner.nextLine();
            client = this.controller.checkIfExistsUsername(username);
        }
        Notifications.info("Usuari trobat al sistema");
        System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "Password: " + Color.RESET + " ");
        String password = scanner.nextLine();
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
            password = scanner.nextLine();
            attempts++;
        }
        Notifications.info("LOGIN COMPLET!");
        this.controller.loginSuccessful(client);
    }
}
