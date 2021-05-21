package es.cc.esliceu.db.limbo.model;

import java.util.Objects;

public class DetallCompra {

    private Compra compra;
    private Producte producte;
    private Double pvp;
    private Double pes;
    private Integer unitats_producte;

    public DetallCompra() {
    }

    public DetallCompra(Compra compra, Producte producte) {
        this.compra = compra;
        this.producte = producte;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producte getProducte() {
        return producte;
    }

    public void setProducte(Producte producte) {
        this.producte = producte;
    }

    public Double getPvp() {
        return pvp;
    }

    public void setPvp(Double pvp) {
        this.pvp = pvp;
    }

    public Double getPes() {
        return pes;
    }

    public void setPes(Double pes) {
        this.pes = pes;
    }

    public Integer getUnitats_producte() {
        return unitats_producte;
    }

    public void setUnitats_producte(Integer unitats_producte) {
        this.unitats_producte = unitats_producte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallCompra that = (DetallCompra) o;
        return Objects.equals(compra, that.compra) && Objects.equals(producte, that.producte) && Objects.equals(pvp, that.pvp) && Objects.equals(pes, that.pes) && Objects.equals(unitats_producte, that.unitats_producte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compra, producte, pvp, pes, unitats_producte);
    }

    @Override
    public String toString() {
        return "DetallCompra{" +
                "compra=" + compra +
                ", producte=" + producte +
                ", pvp=" + pvp +
                ", pes=" + pes +
                ", unitats_producte=" + unitats_producte +
                '}';
    }
}
