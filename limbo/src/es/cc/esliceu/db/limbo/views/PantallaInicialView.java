package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaInicialController;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;
import es.cc.esliceu.db.limbo.util.Notifications;

public class PantallaInicialView {

    private static PantallaInicialView instance;
    private final PantallaInicialController controller;

    private PantallaInicialView () {
        this.controller = PantallaInicialController.getInstance();
    }

    public void init(){
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**             Limbo app               **");
        System.out.println("*****************************************" + Color.RESET);
        System.out.println(Color.BLUE_BOLD_BRIGHT + "1) " + Color.RESET + "Login");
        System.out.println(Color.BLUE_BOLD_BRIGHT + "2) " + Color.RESET + "Registrar-se");
        System.out.println(Color.BLUE_BOLD_BRIGHT + "h) " + Color.RESET + "Ajuda");
        System.out.println(Color.BLUE_BOLD_BRIGHT + "x) " + Color.RESET + "Sortir");
        System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "\t" + "Esculli una opció:" + Color.RESET + " ");
        String option = MyScan.getInstance().getScanner().nextLine().toLowerCase();
        while (!option.equals("1") && !option.equals("2") && !option.equals("h") && !option.equals("x")) {
            Notifications.errada("L'opció escollida no està disponible. Torna a intentar-ho");
            System.out.print(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "\t" + "Esculli una opció:" + Color.RESET + " ");
            option = MyScan.getInstance().getScanner().nextLine().toLowerCase();
        }
        controller.nextPage(option);
    }

    public synchronized static PantallaInicialView getInstance() {
        if (instance == null) {
            instance = new PantallaInicialView();
        }
        return instance;
    }
}
