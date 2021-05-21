package es.cc.esliceu.db.limbo.dao.impl;

import es.cc.esliceu.db.limbo.dao.AdrecaDao;
import es.cc.esliceu.db.limbo.dao.DBConnection;
import es.cc.esliceu.db.limbo.model.Adreca;
import es.cc.esliceu.db.limbo.model.Ciutat;
import es.cc.esliceu.db.limbo.model.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class AdrecaDaoImpl implements AdrecaDao {

    private DBConnection connection;

    public AdrecaDaoImpl(DBConnection dbConnection) {

        this.connection = dbConnection;
    }

    @Override
    public Adreca findById(Adreca adreca) {
        return null;
    }

    @Override
    public Collection<Adreca> findAll() {
        return null;
    }

    @Override
    public void save(Adreca adreca) {
        String sql = "insert into adreca values (null, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,adreca.getClient().getNumero_client());
            preparedStatement.setString(2,adreca.getCarrer());
            preparedStatement.setString(3,adreca.getNumero());
            preparedStatement.setInt(4,adreca.getPis());
            preparedStatement.setString(5,adreca.getPorta());
            preparedStatement.setString(6,adreca.getCp());
            preparedStatement.setInt(7,adreca.getCiutat().getId());
            preparedStatement.executeUpdate();

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
    public void update(Adreca adreca) {

    }

    @Override
    public Collection<Adreca> findAllByIdClient(Client client) {
        String sql = "select * from adreca where client_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,client.getNumero_client());
            rs = preparedStatement.executeQuery();

            Collection<Adreca> adreces = new ArrayList<>();
            while (rs.next()) {
                Adreca adreca = new Adreca();
                adreca.setClient(client);
                adreca.setId(rs.getInt("id"));
                adreca.setCarrer(rs.getString("carrer"));
                adreca.setNumero(rs.getString("numero"));
                adreca.setPis(rs.getInt("pis"));
                adreca.setPorta(rs.getString("porta"));
                adreca.setCp(rs.getString("CP"));
                Ciutat ciutat = new Ciutat(rs.getInt("ciutat_id"));
                adreca.setCiutat(ciutat);
                adreces.add(adreca);
            }
            return adreces;

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
