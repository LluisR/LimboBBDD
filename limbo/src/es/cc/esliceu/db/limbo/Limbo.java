package es.cc.esliceu.db.limbo;

import es.cc.esliceu.db.limbo.controller.PantallaInicialController;
import es.cc.esliceu.db.limbo.dao.impl.DBConnectionImpl;
import es.cc.esliceu.db.limbo.model.Compra;
import es.cc.esliceu.db.limbo.util.Color;
import javax.mail.internet.AddressException;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Limbo {

    public static void main(String[] args) {
       PantallaInicialController pantallaInicialController = new PantallaInicialController();
       pantallaInicialController.init();
    }
}
