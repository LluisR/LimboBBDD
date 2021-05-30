package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaRegistreController;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;
import es.cc.esliceu.db.limbo.util.Notifications;

public class PantallaRegistreView {

    private static PantallaRegistreView instance;
    private final PantallaRegistreController controller;

    private PantallaRegistreView() {
        this.controller = PantallaRegistreController.getInstance();
    }

    public void init() {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**              Registre               **");
        System.out.println("*****************************************" + Color.RESET);
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "\t" + "Premi (x) per cancelar registre..." + Color.RESET + " ");
        System.out.println("Username:");
        String username = MyScan.getInstance().getScanner().nextLine();
        while (username.length() == 0) {
            Notifications.errada("L'username no pot estar buit. Torna a intentar-ho");
            System.out.println("Username:");
            username = MyScan.getInstance().getScanner().nextLine();
        }
        if (username.toLowerCase().equals("x")) this.controller.goBack();
        while (this.controller.checkIfExistsUsername(username)) {
            Notifications.errada("Error: username ja existeix. Provi un altre usuari");
            System.out.println("Username:");
            username = MyScan.getInstance().getScanner().nextLine();
        }
        System.out.println("Email:");
        String email = MyScan.getInstance().getScanner().nextLine();
        if (email.toLowerCase().equals("x")) this.controller.goBack();
        while (!email.matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
            Notifications.errada("Email no vàlid. Per favor, torna a intentar-ho");
            System.out.println("Email:");
            email = MyScan.getInstance().getScanner().nextLine();
        }
        while (this.controller.checkIfExistsEmail(email)) {
            Notifications.errada("Error: email ja existeix. Provi un altre email");
            System.out.println("Email:");
            email = MyScan.getInstance().getScanner().nextLine();
        }
        String referenciaGenerada = this.controller.generateReference();
        this.controller.sendEmail(email, referenciaGenerada);
        System.out.println("Referència:");
        String referencia = MyScan.getInstance().getScanner().nextLine();
        if (referencia.toLowerCase().equals("x")) this.controller.goBack();
        while(!this.controller.checkReference(referencia, referenciaGenerada)) {
            Notifications.errada("Error: la referència introduida no coincideix. Provi de vell nou");
            System.out.println("Referència:");
            referencia = MyScan.getInstance().getScanner().nextLine();
        }
        System.out.println("EMAIL CONFIRMAT");
        System.out.println("Contrasenya: ");
        String contrasenya = MyScan.getInstance().getScanner().nextLine();
        if (contrasenya.toLowerCase().equals("x")) this.controller.goBack();
        while (contrasenya.length() == 0) {
            Notifications.errada("La contrasenya no pot estar buida. Introdueix una contrasenya, per favor.");
            System.out.println("Contrasenya: ");
            contrasenya = MyScan.getInstance().getScanner().nextLine();
        }
        System.out.println("Nom: ");
        String nom = MyScan.getInstance().getScanner().nextLine();
        if (nom.toLowerCase().equals("x")) this.controller.goBack();
        while (nom.length() == 0) {
            Notifications.errada("El nom no pot estar buida. Introdueix una contrasenya, per favor.");
            System.out.println("Nom: ");
            nom = MyScan.getInstance().getScanner().nextLine();
        }
        System.out.println("Cognom1: ");
        String cognom1 = MyScan.getInstance().getScanner().nextLine();
        if (cognom1.toLowerCase().equals("x")) this.controller.goBack();
        while (cognom1.length() == 0) {
            Notifications.errada("El cognom1 no pot estar buida. Introdueix una contrasenya, per favor.");
            System.out.println("Cognom1: ");
            cognom1 = MyScan.getInstance().getScanner().nextLine();
        }
        System.out.println("Cognom2: ");
        String cognom2 = MyScan.getInstance().getScanner().nextLine();
        if (cognom2.toLowerCase().equals("x")) this.controller.goBack();
        Long newId = this.controller.saveNewUser(username, email, contrasenya, nom, cognom1, cognom2);
        if (newId != null) {
            System.out.println(Color.BLUE + "Usuari " + username + " creat correctament amb id " + newId + Color.RESET);
            this.controller.registerSuccessful();
        }
        else Notifications.errada("Hi ha hagut un problema amb el registre del nou usuari...");
    }

    public synchronized static PantallaRegistreView getInstance() {
        if (instance == null) {
            instance = new PantallaRegistreView();
        }
        return instance;
    }
}
