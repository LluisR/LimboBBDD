package es.cc.esliceu.db.limbo.model;

import java.util.Objects;

public class Adreca {

    private Integer id;
    private Client client;
    private String carrer;
    private String numero;
    private Integer pis;
    private String porta;
    private String cp;
    private Ciutat ciutat;

    public Adreca() {
    }

    public Adreca(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getPis() {
        return pis;
    }

    public void setPis(Integer pis) {
        this.pis = pis;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Ciutat getCiutat() {
        return ciutat;
    }

    public void setCiutat(Ciutat ciutat) {
        this.ciutat = ciutat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adreca adreca = (Adreca) o;
        return Objects.equals(id, adreca.id) && Objects.equals(client, adreca.client) && Objects.equals(carrer, adreca.carrer) && Objects.equals(numero, adreca.numero) && Objects.equals(pis, adreca.pis) && Objects.equals(porta, adreca.porta) && Objects.equals(cp, adreca.cp) && Objects.equals(ciutat, adreca.ciutat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, carrer, numero, pis, porta, cp, ciutat);
    }

    @Override
    public String toString() {
        return "Adreca{" +
                "id=" + id +
                ", client=" + client +
                ", carrer='" + carrer + '\'' +
                ", numero='" + numero + '\'' +
                ", pis=" + pis +
                ", porta='" + porta + '\'' +
                ", cp='" + cp + '\'' +
                ", ciutat=" + ciutat +
                '}';
    }
}
