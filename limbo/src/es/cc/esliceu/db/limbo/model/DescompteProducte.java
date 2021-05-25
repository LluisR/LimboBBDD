package es.cc.esliceu.db.limbo.model;

import java.util.Objects;

public class DescompteProducte {

    private Descompte descompte;
    private Producte producte;

    public DescompteProducte(Descompte descompte) {
        this.descompte = descompte;
    }

    public DescompteProducte() {
    }

    public Descompte getDescompte() {
        return descompte;
    }

    public void setDescompte(Descompte descompte) {
        this.descompte = descompte;
    }

    public Producte getProducte() {
        return producte;
    }

    public void setProducte(Producte producte) {
        this.producte = producte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DescompteProducte that = (DescompteProducte) o;
        return Objects.equals(descompte, that.descompte) && Objects.equals(producte, that.producte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descompte, producte);
    }

    @Override
    public String toString() {
        return "DescompteProducte{" +
                "descompte=" + descompte +
                ", producte=" + producte +
                '}';
    }
}
