package es.cc.esliceu.db.limbo.dao.impl;

import es.cc.esliceu.db.limbo.dao.DBConnection;
import es.cc.esliceu.db.limbo.dao.DescompteProducteDao;
import es.cc.esliceu.db.limbo.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class DescompteProducteDaoImpl implements DescompteProducteDao {

    private static DescompteProducteDaoImpl instance;
    private DBConnection connection;

    private DescompteProducteDaoImpl(DBConnection dbConnection) {
        this.connection = dbConnection;
    }

    @Override
    public DescompteProducte findById(DescompteProducte descompteProducte) {
        return null;
    }

    @Override
    public Collection<DescompteProducte> findAll() {
        return null;
    }

    @Override
    public void save(DescompteProducte descompteProducte) {

    }

    @Override
    public void update(DescompteProducte descompteProducte) {

    }

    @Override
    public void delete(DescompteProducte descompteProducte) {

    }

    @Override
    public Collection<DescompteProducte> findAllByIdDescompte(Descompte descompte) {
        String sql = "select * from descompte_producte where descompte_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,descompte.getId());
            rs = preparedStatement.executeQuery();

            Collection<DescompteProducte> descomptes = new ArrayList<>();
            while (rs.next()) {
                Producte producte = new Producte(rs.getInt("producte_id"));
                DescompteProducte descompteProducte = new DescompteProducte();
                descompteProducte.setProducte(producte);
                descompteProducte.setDescompte(descompte);
                descomptes.add(descompteProducte);
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

    public synchronized static DescompteProducteDaoImpl getInstance() {
        if (instance == null) {
            instance = new DescompteProducteDaoImpl(DBConnectionImpl.getInstance());
        }
        return instance;
    }
}
