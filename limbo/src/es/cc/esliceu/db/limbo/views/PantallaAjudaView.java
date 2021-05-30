package es.cc.esliceu.db.limbo.views;

import es.cc.esliceu.db.limbo.controller.PantallaAjudaController;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.util.Color;
import es.cc.esliceu.db.limbo.util.MyScan;

public class PantallaAjudaView {

    private static PantallaAjudaView instance;
    private final PantallaAjudaController controller;

    private PantallaAjudaView () {
        this.controller = PantallaAjudaController.getInstance();
    }

    public void init(Client client) {
        this.printHelp();
        this.controller.goBack(client);
    }

    private void printHelp() {
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************");
        System.out.println("**               Ajuda                 **");
        System.out.println(Color.YELLOW_BRIGHT + "*****************************************" + Color.RESET);
        System.out.println(Color.WHITE_BRIGHT + "                        ** LIMBO **                " + Color.RESET);
        System.out.println(Color.CYAN_BOLD_BRIGHT + "Limbo es una tenda d'una selecció dels millors productes de la terra, 100% saludables i ecológics." + Color.RESET);
        System.out.println("    - Per poder accedir a la nostra tenda, hauràs de crear un perfil d'usuari mitjançant el formulari de registe (2)");
        System.out.println("    - Si ja tens un compte a la nostra plataforma, podrás accedir a través del login (1)");
        System.out.println("    - Una vegada dins la plataforma, podrás fer el següent:");
        System.out.println(Color.GREEN + "             1. Visualitzar tots els productes disponibles a la nostra tenda.");
        System.out.println(Color.MAGENTA + "             2. Filtrar per categoria, nom, descripció i marca.");
        System.out.println(Color.YELLOW + "             3. Realitzar compres al millor preu.");
        System.out.println(Color.CYAN + "             4. Gaudir de promocions i codis de descompte exclusius.");
        System.out.println(Color.RED + "             5. Veure l'historial de compres realitzades a la plataforma.");
        System.out.println(Color.BLUE + "             6. Modificar les teves dades personals per tenir-les sempre actualitzades ;-) ." + Color.RESET);
        System.out.println("    - Tot això i més, ho podrás trobar a Limbo, el teu supermercat de confiança");
        System.out.println(Color.CYAN_BOLD_BRIGHT + "Moltes gràcies per confiar amb Limbo." + Color.RESET);
        System.out.println(Color.YELLOW_BACKGROUND + "" + Color.BLACK_BOLD + "\t" + "Premi qualsevol botó per tornar a enrere" + Color.RESET + " ");
        MyScan.getInstance().getScanner().nextLine();
    }

    public synchronized static PantallaAjudaView getInstance() {
        if (instance == null) {
            instance = new PantallaAjudaView();
        }
        return instance;
    }
}
