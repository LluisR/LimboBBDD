package es.cc.esliceu.db.limbo.model;

import java.util.Objects;

public class Provincia {

    private Integer id;
    private String nom;

    public Provincia() {
    }

    public Provincia(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provincia provincia = (Provincia) o;
        return Objects.equals(id, provincia.id) && Objects.equals(nom, provincia.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
