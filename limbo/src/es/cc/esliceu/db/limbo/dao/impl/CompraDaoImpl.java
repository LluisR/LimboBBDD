package es.cc.esliceu.db.limbo.dao.impl;

import es.cc.esliceu.db.limbo.dao.CompraDao;
import es.cc.esliceu.db.limbo.dao.DBConnection;
import es.cc.esliceu.db.limbo.model.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class CompraDaoImpl implements CompraDao {

    private static CompraDaoImpl instance;
    private DBConnection connection;

    private CompraDaoImpl(DBConnection dbConnection) {
        this.connection = dbConnection;
    }


    @Override
    public Compra findById(Compra compra) {
        return null;
    }

    @Override
    public Collection<Compra> findAll() {
        return null;
    }

    @Override
    public Integer save(Compra compra) {
        String sql = "insert into compra values (null, ?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = Date.valueOf(formatter.format(compra.getData()));

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,compra.getTargeta().getId());
            preparedStatement.setInt(2,compra.getAdreca().getId());
            preparedStatement.setInt(3,compra.getClient().getNumero_client());
            preparedStatement.setString(4,compra.getId_transaccio());
            preparedStatement.setDate(5, date);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
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
    public void update(Compra compra) {

    }

    @Override
    public void updateByTargeta(Targeta targeta) {
        String sql = "update compra set targeta_id=null where targeta_id=?;";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,targeta.getId());
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

    @Override
    public void updateByAdress(Adreca adreca) {
        String sql = "update compra set adreca_id=null where adreca_id=?;";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,adreca.getId());
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

    @Override
    public Collection<Compra> findByIdClient(Client client) {
        String sql = "select * from compra where client_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,client.getNumero_client());
            rs = preparedStatement.executeQuery();

            Collection<Compra> compres = new ArrayList<>();
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setClient(client);
                Targeta targeta = new Targeta();
                targeta.setId(rs.getInt("targeta_id"));
                compra.setTargeta(targeta);
                Adreca adreca = new Adreca();
                adreca.setId(rs.getInt("adreca_id"));
                compra.setAdreca(adreca);
                compra.setId_transaccio(rs.getString("id_transaccio"));
                compra.setData(rs.getDate("data"));
                compres.add(compra);
            }
            return compres;

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

    public synchronized static CompraDaoImpl getInstance() {
        if (instance == null) {
            instance = new CompraDaoImpl(DBConnectionImpl.getInstance());
        }
        return instance;
    }
}
