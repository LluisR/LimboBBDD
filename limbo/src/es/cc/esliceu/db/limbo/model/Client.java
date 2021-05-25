package es.cc.esliceu.db.limbo.model;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class Client {

    private Integer numero_client;
    private String email;
    private String nom;
    private String cognom1;
    private String cognom2;
    private String username;
    private String contrasenya;
    private Compra compra;
    private Collection<Targeta> targetes;
    private Collection<Adreca> adreces;
    private Collection<Compra> compres;
    private Map<Descompte, Collection<Producte>> descomptes;

    public Client() {
    }

    public Client(Integer numero_client) {
        this.numero_client = numero_client;
    }

    public Integer getNumero_client() {
        return numero_client;
    }

    public void setNumero_client(Integer numero_client) {
        this.numero_client = numero_client;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public void setCognom1(String cognom1) {
        this.cognom1 = cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra cistella) {
        this.compra = cistella;
    }

    public Collection<Targeta> getTargetes() {
        return targetes;
    }

    public void setTargetes(Collection<Targeta> targetes) {
        this.targetes = targetes;
    }

    public Collection<Adreca> getAdreces() {
        return adreces;
    }

    public void setAdreces(Collection<Adreca> adreces) {
        this.adreces = adreces;
    }

    public Collection<Compra> getCompres() {
        return compres;
    }

    public void setCompres(Collection<Compra> compres) {
        this.compres = compres;
    }

    public Map<Descompte, Collection<Producte>> getDescomptes() {
        return descomptes;
    }

    public void setDescomptes(Map<Descompte, Collection<Producte>> descomptes) {
        this.descomptes = descomptes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(numero_client, client.numero_client) && Objects.equals(email, client.email) && Objects.equals(nom, client.nom) && Objects.equals(cognom1, client.cognom1) && Objects.equals(cognom2, client.cognom2) && Objects.equals(username, client.username) && Objects.equals(contrasenya, client.contrasenya) && Objects.equals(compra, client.compra) && Objects.equals(targetes, client.targetes) && Objects.equals(adreces, client.adreces) && Objects.equals(compres, client.compres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero_client, email, nom, cognom1, cognom2, username, contrasenya, compra, targetes, adreces, compres);
    }

    @Override
    public String toString() {
        return "Client{" +
                "numero_client=" + numero_client +
                ", email='" + email + '\'' +
                ", nom='" + nom + '\'' +
                ", cognom1='" + cognom1 + '\'' +
                ", cognom2='" + cognom2 + '\'' +
                ", username='" + username + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", compra=" + compra +
                ", targetes=" + targetes +
                ", adreces=" + adreces +
                ", compres=" + compres +
                '}';
    }
}
