package es.cc.esliceu.db.limbo.dao.impl;

import es.cc.esliceu.db.limbo.dao.DBConnection;
import es.cc.esliceu.db.limbo.dao.DetallCompraDao;
import es.cc.esliceu.db.limbo.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DetallCompraDaoImpl implements DetallCompraDao {

    private static DetallCompraDaoImpl instance;
    private DBConnection connection;

    private DetallCompraDaoImpl(DBConnection dbConnection) {
        this.connection = dbConnection;
    }

    @Override
    public DetallCompra findById(DetallCompra detallCompra) {
        return null;
    }

    @Override
    public Collection<DetallCompra> findAll() {
        return null;
    }

    @Override
    public void save(DetallCompra detallCompra) {
        String sql = "insert into detall_compra values (?,?,?,?,?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,detallCompra.getCompra().getId());
            preparedStatement.setInt(2,detallCompra.getProducte().getId());
            if (detallCompra.getPvp() == null) {
                preparedStatement.setNull(3, Types.DOUBLE);
            } else {
                preparedStatement.setDouble(3,detallCompra.getPvp());
            }

            if (detallCompra.getPes() == null) {
                preparedStatement.setNull(4, Types.DOUBLE);
            } else {
                preparedStatement.setDouble(4,detallCompra.getPes());
            }
            preparedStatement.setInt(5, detallCompra.getUnitats_producte());
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
    public void update(DetallCompra detallCompra) {

    }

    @Override
    public Collection<DetallCompra> findByIdCompra(Compra compra) {

        String sql = "select * from detall_compra where compra_id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,compra.getId());
            rs = preparedStatement.executeQuery();

            Collection<DetallCompra> detallCompres = new ArrayList<>();
            while (rs.next()) {
                DetallCompra detallCompra = new DetallCompra();
                detallCompra.setCompra(compra);
                detallCompra.setProducte(new Producte(rs.getInt("producte_id")));
                detallCompra.setPvp(rs.getDouble("pvp"));
                detallCompra.setPes(rs.getDouble("pes"));
                detallCompra.setUnitats_producte(rs.getInt("unitats_producte"));
                detallCompres.add(detallCompra);
            }
            return detallCompres;

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

    public synchronized static DetallCompraDaoImpl getInstance() {
        if (instance == null) {
            instance = new DetallCompraDaoImpl(DBConnectionImpl.getInstance());
        }
        return instance;
    }

}
