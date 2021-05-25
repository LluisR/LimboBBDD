package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.views.PantallaInicialView;

public class PantallaInicialController {

    private static PantallaInicialController instance;

    private PantallaInicialController() { }

    public void init() {
        PantallaInicialView.getInstance().init();
    }

    public void nextPage(String option) {
        switch (option) {
            case "1": PantallaLoginController.getInstance().init(); break;
            case "2": PantallaRegistreController.getInstance().init(); break;
            case "h": PantallaAjudaController.getInstance().init(); break;
            case "x": ;break;
        }
    }

    public synchronized static PantallaInicialController getInstance() {
        if (instance == null) {
            instance = new PantallaInicialController();
        }
        return instance;
    }
}
