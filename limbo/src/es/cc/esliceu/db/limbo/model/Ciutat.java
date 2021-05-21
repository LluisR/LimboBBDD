package es.cc.esliceu.db.limbo.model;

import java.util.Objects;

public class Ciutat {

    private Integer id;
    private String nom;
    private Provincia provincia;

    public Ciutat() {
    }

    public Ciutat(Integer id) {
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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciutat ciutat = (Ciutat) o;
        return Objects.equals(id, ciutat.id) && Objects.equals(nom, ciutat.nom) && Objects.equals(provincia, ciutat.provincia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, provincia);
    }

    @Override
    public String toString() {
        return "Ciutat{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", provincia=" + provincia +
                '}';
    }
}
