package es.cc.esliceu.db.limbo.model;

import java.util.Date;
import java.util.Objects;

public class Compra {

    private Integer id;
    private Targeta targeta;
    private Adreca adreca;
    private Client client;
    private String id_transaccio;
    private Date data;

    public Compra() {
    }

    public Compra(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Targeta getTargeta() {
        return targeta;
    }

    public void setTargeta(Targeta targeta) {
        this.targeta = targeta;
    }

    public Adreca getAdreca() {
        return adreca;
    }

    public void setAdreca(Adreca adreca) {
        this.adreca = adreca;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getId_transaccio() {
        return id_transaccio;
    }

    public void setId_transaccio(String id_transaccio) {
        this.id_transaccio = id_transaccio;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return Objects.equals(id, compra.id) && Objects.equals(targeta, compra.targeta) && Objects.equals(adreca, compra.adreca) && Objects.equals(client, compra.client) && Objects.equals(id_transaccio, compra.id_transaccio) && Objects.equals(data, compra.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, targeta, adreca, client, id_transaccio, data);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", targeta=" + targeta +
                ", adreca=" + adreca +
                ", client=" + client +
                ", id_transaccio='" + id_transaccio + '\'' +
                ", data=" + data +
                '}';
    }
}
