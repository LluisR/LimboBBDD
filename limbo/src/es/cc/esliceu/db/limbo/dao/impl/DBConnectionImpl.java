package es.cc.esliceu.db.limbo.dao.impl;

import es.cc.esliceu.db.limbo.dao.DBConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class DBConnectionImpl implements DBConnection {

    // SINGLETON PATTERN
    private static DBConnectionImpl instance;
    private Connection connection;

    private DBConnectionImpl() {
        Scanner scanner = new Scanner(System.in);
        String[] credentials = this.getCredentials();
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            this.connection = DriverManager.getConnection(credentials[0], credentials[1], credentials[2]);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String[] getCredentials() {
        String [] credentials = new String[3];
        try {
            FileInputStream input = new FileInputStream("resources/limbo.properties");
            Properties properties = new Properties();
            properties.load(input);
            credentials[0] = properties.getProperty("HOST");
            credentials[1] = properties.getProperty("USER");
            credentials[2] = properties.getProperty("PASSWORD");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    public synchronized static DBConnectionImpl getInstance() {
        if (instance == null) {
            instance = new DBConnectionImpl();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
