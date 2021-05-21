package es.cc.esliceu.db.limbo.dao.impl;

import es.cc.esliceu.db.limbo.dao.DBConnection;
import es.cc.esliceu.db.limbo.dao.ProducteDao;
import es.cc.esliceu.db.limbo.model.Categoria;
import es.cc.esliceu.db.limbo.model.DetallCompra;
import es.cc.esliceu.db.limbo.model.Producte;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class ProducteDaoImpl implements ProducteDao {

    private DBConnection connection;

    public ProducteDaoImpl(DBConnection dbConnection) {

        this.connection = dbConnection;
    }

    @Override
    public Producte findById(Integer id) {
        String sql = "select * from producte where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Producte producte = new Producte();
                producte.setId(rs.getInt("id"));
                producte.setNom(rs.getString("nom"));
                producte.setDescripcio(rs.getString("descripcio"));
                producte.setPvp(rs.getDouble("pvp"));
                producte.setPes(rs.getDouble("pes"));
                producte.setIva(rs.getInt("iva"));
                producte.setMarca(rs.getString("marca"));
                producte.setUnitat_mesura(rs.getString("unitat_mesura"));
                Categoria categoria = new Categoria(rs.getInt("categoria"));
                producte.setCategoria(categoria);
                return producte;
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
    public Collection<Producte> findAll() {
        return null;
    }

    @Override
    public void save(Producte producte) {

    }

    @Override
    public void update(Producte producte) {

    }

    @Override
    public Collection<Producte> findSuggested() {
        String sql = "select * from producte order by rand() limit 5";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Collection<Producte> productes = new ArrayList<>();

        try {
            preparedStatement = this.connection.getConnection().prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Producte producte = new Producte();
                producte.setId(rs.getInt("id"));
                producte.setNom(rs.getString("nom"));
                producte.setDescripcio(rs.getString("descripcio"));
                producte.setPvp(rs.getDouble("pvp"));
                producte.setIva(rs.getInt("iva"));
                producte.setMarca(rs.getString("marca"));
                producte.setUnitat_mesura(rs.getString("unitat_mesura"));
                producte.setPes(rs.getDouble("pes"));
                Categoria categoria = new Categoria(rs.getInt("categoria"));
                producte.setCategoria(categoria);
                productes.add(producte);
            }
            return productes;

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
    public Collection<Producte> findWithFilters(Map<String,Integer> mapaFiltres, String sql, String nom, String descripcio, String marca, String categoria) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Collection<Producte> productes = null;

        try {
            statement = connection.getConnection().prepareStatement(sql);

            if (nom.length() > 0) {
                Integer pos = mapaFiltres.get("nomProducte");
                statement.setString(pos, nom);
            }
            if (descripcio.length() > 0) {
                Integer pos = mapaFiltres.get("descripcioProducte");
                statement.setString(pos, descripcio);
            }
            if (marca.length() > 0 ) {
                Integer pos = mapaFiltres.get("marcaProducte");
                statement.setString(pos, marca);
            }
            if (categoria.length() > 0) {
                Integer pos = mapaFiltres.get("categoria");
                statement.setInt(pos, Integer.parseInt(categoria));
            }

            rs = statement.executeQuery();
            productes = new ArrayList<>();
            while (rs.next()) {
                Producte producte = new Producte();
                producte.setId(rs.getInt("id"));
                producte.setNom(rs.getString("nom"));
                producte.setDescripcio(rs.getString("descripcio"));
                producte.setPvp(rs.getDouble("pvp"));
                producte.setIva(rs.getInt("iva"));
                producte.setMarca(rs.getString("marca"));
                producte.setUnitat_mesura(rs.getString("unitat_mesura"));
                producte.setPes(rs.getDouble("pes"));
                Categoria newCategoria = new Categoria(rs.getInt("categoria"));
                producte.setCategoria(newCategoria);
                productes.add(producte);
            }
            return productes;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement!=null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
