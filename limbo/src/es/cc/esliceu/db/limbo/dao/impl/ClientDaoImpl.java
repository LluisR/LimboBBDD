package es.cc.esliceu.db.limbo.dao.impl;

import es.cc.esliceu.db.limbo.dao.ClientDao;
import es.cc.esliceu.db.limbo.dao.DBConnection;
import es.cc.esliceu.db.limbo.model.Client;

import java.sql.*;
import java.util.Collection;

public class ClientDaoImpl implements ClientDao {

    private static ClientDaoImpl instance;
    private DBConnection connection;

    private ClientDaoImpl(DBConnection dbConnection) {
        this.connection = dbConnection;
    }

    @Override
    public Client findById(Integer id) {
        return null;
    }

    @Override
    public Client findByUserName(String username) {
        String sql = "select * from client where username = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,username);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setNumero_client(rs.getInt("numero_client"));
                client.setEmail(rs.getString("email"));
                client.setNom(rs.getString("nom"));
                client.setCognom1(rs.getString("cognom1"));
                client.setCognom2(rs.getString("cognom2"));
                client.setUsername(rs.getString("username"));
                client.setContrasenya(rs.getString("contrasenya"));
                return client;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Client findByEmail(String email) {
        String sql = "select * from client where email = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,email);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setNumero_client(rs.getInt("numero_client"));
                client.setEmail(rs.getString("email"));
                client.setNom(rs.getString("nom"));
                client.setCognom1(rs.getString("cognom1"));
                client.setCognom2(rs.getString("cognom2"));
                client.setUsername(rs.getString("username"));
                client.setContrasenya(rs.getString("contrasenya"));
                return client;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Collection<Client> findAll() {
        return null;
    }

    @Override
    public Long save(Client client) {
        String sql = "insert into client values (null, ?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,client.getEmail());
            preparedStatement.setString(2,client.getNom());
            preparedStatement.setString(3,client.getCognom1());
            if (client.getCognom2().length() == 0) {
                preparedStatement.setNull(4, Types.VARCHAR);
            } else {
                preparedStatement.setString(4,client.getCognom2());
            }
            preparedStatement.setString(5,client.getUsername());
            preparedStatement.setString(6,client.getContrasenya());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void update(Client client) {
        String sql = "update client set email=?, nom=?, cognom1=?, cognom2=?, username=? where numero_client=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,client.getEmail());
            preparedStatement.setString(2,client.getNom());
            preparedStatement.setString(3,client.getCognom1());
            preparedStatement.setString(4,client.getCognom2());
            preparedStatement.setString(5,client.getUsername());
            preparedStatement.setInt(6,client.getNumero_client());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static ClientDaoImpl getInstance() {
        if (instance == null) {
            instance = new ClientDaoImpl(DBConnectionImpl.getInstance());
        }
        return instance;
    }
}
