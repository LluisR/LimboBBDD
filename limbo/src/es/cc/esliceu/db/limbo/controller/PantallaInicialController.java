package es.cc.esliceu.db.limbo.controller;

import es.cc.esliceu.db.limbo.views.PantallaInicialView;

public class PantallaInicialController {

    public void init() {
        PantallaInicialView.getInstance().init();
    }

    public void nextPage(String option) {
        switch (option) {
            case "1": PantallaLoginController login = new PantallaLoginController(); login.init(); break;
            case "2": PantallaRegistreController registre = new PantallaRegistreController(); registre.init(); break;
            case "h": PantallaAjudaController pantallaAjudaController = new PantallaAjudaController(); pantallaAjudaController.init(); break;
            case "x": ;break;
        }
    }
}
