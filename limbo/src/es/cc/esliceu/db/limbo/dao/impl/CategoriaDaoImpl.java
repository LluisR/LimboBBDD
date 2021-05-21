package es.cc.esliceu.db.limbo.dao.impl;

import es.cc.esliceu.db.limbo.dao.CategoriaDao;
import es.cc.esliceu.db.limbo.dao.DBConnection;
import es.cc.esliceu.db.limbo.model.Categoria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class CategoriaDaoImpl implements CategoriaDao {

    private DBConnection connection;

    public CategoriaDaoImpl(DBConnection dbConnection) {

        this.connection = dbConnection;
    }

    @Override
    public Categoria findById(Categoria categoria) {
        return null;
    }

    @Override
    public Collection<Categoria> findAll() {
        String sql = "select * from categoria";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Collection<Categoria> categories = new ArrayList<>();

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNom(rs.getString("nom"));
                categoria.setDescripcio(rs.getString("descripcio"));
                categories.add(categoria);
            }
            return categories;

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
    public void save(Categoria categoria) {

    }

    @Override
    public void update(Categoria categoria) {

    }
}
