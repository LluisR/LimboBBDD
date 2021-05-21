package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaRegistreController;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.Notifications;

import java.util.Scanner;

public class PantallaRegistreView {

    private final PantallaRegistreController controller;

    public PantallaRegistreView() {
        this.controller = new PantallaRegistreController();
    }

    public void init() {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**              Registre               **");
        System.out.println("*****************************************" + Color.RESET);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username:");
        String username = scanner.nextLine();
        while (this.controller.checkIfExistsUsername(username)) {
            Notifications.errada("Error: username ja existeix. Provi un altre usuari");
            System.out.println("Username:");
            username = scanner.nextLine();
        }
        System.out.println("Email:");
        String email = scanner.nextLine();
        while (this.controller.checkIfExistsEmail(email)) {
            Notifications.errada("Error: email ja existeix. Provi un altre email");
            System.out.println("Email:");
            email = scanner.nextLine();
        }
        String referenciaGenerada = this.controller.generateReference();
        this.controller.sendEmail(email, referenciaGenerada);
        System.out.println("Referència:");
        String referencia = scanner.nextLine();
        while(!this.controller.checkReference(referencia, referenciaGenerada)) {
            Notifications.errada("Error: la referència introduida no coincideix. Provi de vell nou");
            System.out.println("Referència:");
            referencia = scanner.nextLine();
        }
        System.out.println("EMAIL CONFIRMAT");
        System.out.println("Contrasenya: ");
        String contrasenya = scanner.nextLine();
        System.out.println("Nom: ");
        String nom = scanner.nextLine();
        System.out.println("Cognom1: ");
        String cognom1 = scanner.nextLine();
        System.out.println("Cognom2: ");
        String cognom2 = scanner.nextLine();
        Long newId = this.controller.saveNewUser(username, email, contrasenya, nom, cognom1, cognom2);
        if (newId != null) {
            System.out.println(Color.BLUE + "Usuari " + username + " creat correctament amb id " + newId + Color.RESET);
            this.controller.registerSuccessful();
        }
        else Notifications.errada("Hi ha hagut un problema amb el registre del nou usuari...");
    }
}
