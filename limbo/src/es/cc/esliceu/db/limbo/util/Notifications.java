package es.cc.esliceu.db.limbo.util;

public class Notifications {
    public static void info(String texte){
        System.out.println(Color.BLUE_BOLD + "\t" + texte + Color.RESET);
    }
    public static void errada(String texte){
        System.out.println(Color.RED_BOLD + "\t" + texte + Color.RESET);
    }
}
