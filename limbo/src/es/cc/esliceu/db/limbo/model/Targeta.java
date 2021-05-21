package es.cc.esliceu.db.limbo.model;

import java.util.Date;
import java.util.Objects;

public class Targeta {

    private Integer id;
    private Long numero;
    private String tipus;
    private Date data_caducitat;
    private Integer codi_seguretat;
    private Client client;

    public Targeta() {
    }

    public Targeta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Date getData_caducitat() {
        return data_caducitat;
    }

    public void setData_caducitat(Date data_caducitat) {
        this.data_caducitat = data_caducitat;
    }

    public Integer getCodi_seguretat() {
        return codi_seguretat;
    }

    public void setCodi_seguretat(Integer codi_seguretat) {
        this.codi_seguretat = codi_seguretat;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Targeta targeta = (Targeta) o;
        return Objects.equals(id, targeta.id) && Objects.equals(numero, targeta.numero) && Objects.equals(tipus, targeta.tipus) && Objects.equals(data_caducitat, targeta.data_caducitat) && Objects.equals(codi_seguretat, targeta.codi_seguretat) && Objects.equals(client, targeta.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, tipus, data_caducitat, codi_seguretat, client);
    }

    @Override
    public String toString() {
        return "Targeta{" +
                "id=" + id +
                ", numero=" + numero +
                ", tipus='" + tipus + '\'' +
                ", data_caducitat=" + data_caducitat +
                ", codi_seguretat=" + codi_seguretat +
                ", client=" + client +
                '}';
    }
}
