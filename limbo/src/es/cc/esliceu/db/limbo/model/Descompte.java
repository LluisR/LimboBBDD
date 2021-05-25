package es.cc.esliceu.db.limbo.model;

import java.util.Date;
import java.util.Objects;

public class Descompte {

    private Integer id;
    private Integer percentatge;
    private Date dataInici;
    private Date dataFi;

    public Descompte(Integer id) {
        this.id = id;
    }

    public Descompte() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPercentatge() {
        return percentatge;
    }

    public void setPercentatge(Integer percentatge) {
        this.percentatge = percentatge;
    }

    public Date getDataInici() {
        return dataInici;
    }

    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }

    public Date getDataFi() {
        return dataFi;
    }

    public void setDataFi(Date dataFi) {
        this.dataFi = dataFi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Descompte descompte = (Descompte) o;
        return Objects.equals(id, descompte.id) && Objects.equals(percentatge, descompte.percentatge) && Objects.equals(dataInici, descompte.dataInici) && Objects.equals(dataFi, descompte.dataFi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, percentatge, dataInici, dataFi);
    }

    @Override
    public String toString() {
        return "Descompte{" +
                "id=" + id +
                ", percentatge=" + percentatge +
                ", dataInici=" + dataInici +
                ", dataFi=" + dataFi +
                '}';
    }
}
