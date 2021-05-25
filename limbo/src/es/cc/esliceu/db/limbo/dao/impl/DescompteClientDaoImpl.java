package es.cc.esliceu.db.limbo.dao.impl;

import es.cc.esliceu.db.limbo.dao.DBConnection;
import es.cc.esliceu.db.limbo.dao.DescompteClientDao;
import es.cc.esliceu.db.limbo.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class DescompteClientDaoImpl implements DescompteClientDao {

    private DBConnection connection;

    public DescompteClientDaoImpl(DBConnection dbConnection) {

        this.connection = dbConnection;
    }


    @Override
    public DescompteClient findById(DescompteClient descompteClient) {
        return null;
    }

    @Override
    public Collection<DescompteClient> findAll() {
        return null;
    }

    @Override
    public void save(DescompteClient descompteClient) {

    }

    @Override
    public void update(DescompteClient descompteClient) {

    }

    @Override
    public void delete(DescompteClient descompteClient) {

    }

    @Override
    public Collection<DescompteClient> findAllByIdClient(Client client) {
        String sql = "select * from descompte_client where client_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,client.getNumero_client());
            rs = preparedStatement.executeQuery();

            Collection<DescompteClient> descomptes = new ArrayList<>();
            while (rs.next()) {
                Descompte descompte = new Descompte(rs.getInt("descompte_id"));
                DescompteClient descompteClient = new DescompteClient();
                descompteClient.setDescompte(descompte);
                descompteClient.setClient(client);
                descomptes.add(descompteClient);
            }
            return descomptes;

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
