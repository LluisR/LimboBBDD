package es.cc.esliceu.db.limbo.model;

import java.util.Objects;

public class Categoria {

    private Integer id;
    private String nom;
    private String descripcio;

    public Categoria() {
    }

    public Categoria(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id) && Objects.equals(nom, categoria.nom) && Objects.equals(descripcio, categoria.descripcio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, descripcio);
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", descripcio='" + descripcio + '\'' +
                '}';
    }
}
