package es.cc.esliceu.db.limbo.model;

import java.util.*;

public class Cistella {

    private Collection<DetallCompra> productes;
    private Double total;

    public Cistella() {
        this.productes = new ArrayList<>();
        this.total = 0.0;
    }
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Collection<DetallCompra> getProductes() {
        return productes;
    }

    public void setProductes(Collection<DetallCompra> productes) {
        this.productes = productes;
    }

    public void calculaTotal() {
        this.total = 0.0;
        this.productes.forEach((detallCompra) -> {
            this.total += detallCompra.getProducte().getPvp() * detallCompra.getUnitats_producte();
            System.out.println(detallCompra.getProducte().getPvp());
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cistella cistella = (Cistella) o;
        return Objects.equals(productes, cistella.productes) && Objects.equals(total, cistella.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productes, total);
    }

    @Override
    public String toString() {
        return "Cistella{" +
                "productes1=" + productes +
                ", total=" + total +
                '}';
    }
}
