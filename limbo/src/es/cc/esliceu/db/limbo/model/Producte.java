package es.cc.esliceu.db.limbo.model;

import java.util.Objects;

public class Producte {

    private Integer id;
    private String nom;
    private String descripcio;
    private Double pvp;
    private Integer iva;
    private String marca;
    private String unitat_mesura;
    private Double pes;
    private Categoria categoria;

    public Producte() {
    }

    public Producte(Integer id) {
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

    public Double getPvp() {
        return pvp;
    }

    public void setPvp(Double pvp) {
        this.pvp = pvp;
    }

    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUnitat_mesura() {
        return unitat_mesura;
    }

    public void setUnitat_mesura(String unitat_mesura) {
        this.unitat_mesura = unitat_mesura;
    }

    public Double getPes() {
        return pes;
    }

    public void setPes(Double pes) {
        this.pes = pes;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producte producte = (Producte) o;
        return Objects.equals(id, producte.id) && Objects.equals(nom, producte.nom) && Objects.equals(descripcio, producte.descripcio) && Objects.equals(pvp, producte.pvp) && Objects.equals(iva, producte.iva) && Objects.equals(marca, producte.marca) && Objects.equals(unitat_mesura, producte.unitat_mesura) && Objects.equals(pes, producte.pes) && Objects.equals(categoria, producte.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, descripcio, pvp, iva, marca, unitat_mesura, pes, categoria);
    }

    @Override
    public String toString() {
        return "Producte{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", descripcio='" + descripcio + '\'' +
                ", pvp=" + pvp +
                ", iva=" + iva +
                ", marca='" + marca + '\'' +
                ", unitat_mesura='" + unitat_mesura + '\'' +
                ", pes=" + pes +
                ", categoria=" + categoria +
                '}';
    }
}
