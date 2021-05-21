package es.cc.esliceu.db.limbo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class Compra {

    private Integer id;
    private Targeta targeta;
    private Adreca adreca;
    private Client client;
    private String id_transaccio;
    private Date data;
    private Collection<DetallCompra> productes;
    private Double total;

    public Compra() {
        this.productes = new ArrayList<>();
        this.total = 0.0;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Collection<DetallCompra> getProductes() {
        this.calculaTotal();
        return productes;
    }

    public void setProductes(Collection<DetallCompra> productes) {
        this.productes = productes;
        this.calculaTotal();
    }

    private void calculaTotal() {
        this.total = 0.0;
        this.productes.forEach((detallCompra) -> {
            this.total += detallCompra.getProducte().getPvp() * detallCompra.getUnitats_producte();
            this.total = Math.round(this.total * 100.0) / 100.0;
            /*System.out.println("PRECIO PRODUCTO " + detallCompra.getProducte().getPvp());
            System.out.println("UNITADES PRODUCTO " + detallCompra.getUnitats_producte());
            System.out.println("TOTAL " + detallCompra.getProducte().getPvp() * detallCompra.getUnitats_producte());*/
        });
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
