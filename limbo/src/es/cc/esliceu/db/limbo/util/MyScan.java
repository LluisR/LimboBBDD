package es.cc.esliceu.db.limbo.util;

import java.sql.Connection;
import java.util.Scanner;

public class MyScan {

    // SINGLETON PATTERN
    private static MyScan instance;
    private Scanner scanner;

    private MyScan() {
        this.scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    public synchronized static MyScan getInstance() {
        if (instance == null) {
            instance = new MyScan();
        }
        return instance;
    }
}
