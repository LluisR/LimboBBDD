package es.cc.esliceu.db.limbo.dao.impl;

import es.cc.esliceu.db.limbo.dao.DBConnection;
import es.cc.esliceu.db.limbo.dao.DescompteDao;
import es.cc.esliceu.db.limbo.model.Client;
import es.cc.esliceu.db.limbo.model.Descompte;
import sun.security.krb5.internal.crypto.Des;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

public class DescompteDaoImpl implements DescompteDao {

    private DBConnection connection;

    public DescompteDaoImpl(DBConnection dbConnection) {

        this.connection = dbConnection;
    }


    @Override
    public Descompte findById(Descompte descompte) {
        String sql = "select * from descompte where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,descompte.getId());
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                descompte.setDataInici(rs.getDate("data_inici"));
                descompte.setDataFi(rs.getDate("data_fi"));
                descompte.setPercentatge(rs.getInt("percentatge"));
                return descompte;
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
    public Collection<Descompte> findAll() {
        return null;
    }

    @Override
    public void save(Descompte descompte) {

    }

    @Override
    public void update(Descompte descompte) {

    }

    @Override
    public void delete(Descompte descompte) {

    }

    @Override
    public Collection<Descompte> findAllByIdClient(Client client) {

        return null;
    }
}
