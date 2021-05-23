package es.cc.esliceu.db.limbo.dao.impl;

import es.cc.esliceu.db.limbo.dao.DBConnection;
import es.cc.esliceu.db.limbo.dao.TargetaDao;
import es.cc.esliceu.db.limbo.model.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class TargetaDaoImpl implements TargetaDao {

    private DBConnection connection;

    public TargetaDaoImpl(DBConnection dbConnection) {

        this.connection = dbConnection;
    }

    @Override
    public Targeta findById(Targeta targeta) {
        return null;
    }

    @Override
    public Collection<Targeta> findAll() {
        return null;
    }

    @Override
    public void save(Targeta targeta) {
        String sql = "insert into targeta values (null, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = Date.valueOf(formatter.format(targeta.getData_caducitat()));

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, TipusTargeta.valueOf(targeta.getTipus().toString()).toString());
            preparedStatement.setLong(2,targeta.getNumero());
            preparedStatement.setDate(3,date);
            preparedStatement.setInt(4,targeta.getCodi_seguretat());
            preparedStatement.setInt(5,targeta.getClient().getNumero_client());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    targeta.setId(generatedKeys.getInt(1));
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
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Targeta targeta) {

    }

    @Override
    public void delete(Targeta targeta) {
        String sql = "delete from targeta where id=?";
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
    public Collection<Targeta> findByIdClient(Client client) {
        String sql = "select * from targeta where client_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,client.getNumero_client());
            rs = preparedStatement.executeQuery();

            Collection<Targeta> targetes = new ArrayList<>();
            while (rs.next()) {
                Targeta targeta = new Targeta();
                targeta.setId(rs.getInt("id"));
                targeta.setClient(client);
                targeta.setNumero(rs.getLong("numero"));
                targeta.setCodi_seguretat(rs.getInt("codi_seguretat"));
                targeta.setTipus(TipusTargeta.valueOf(rs.getString("tipus")));
                targeta.setData_caducitat(rs.getDate("data_caducitat"));
                targetes.add(targeta);
            }
            return targetes;

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
}
