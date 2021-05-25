package es.cc.esliceu.db.limbo.model;

import java.util.Objects;

public class DescompteClient {

    private Client client;
    private Descompte descompte;

    public DescompteClient() {
    }

    public DescompteClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Descompte getDescompte() {
        return descompte;
    }

    public void setDescompte(Descompte descompte) {
        this.descompte = descompte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DescompteClient that = (DescompteClient) o;
        return Objects.equals(client, that.client) && Objects.equals(descompte, that.descompte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, descompte);
    }

    @Override
    public String toString() {
        return "DescompteClient{" +
                "client=" + client +
                ", descompte=" + descompte +
                '}';
    }
}
