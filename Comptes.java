package com.example.demo1.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "comptes")
public class Comptes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")


    private Long id;

    @Column(name = "owner")
    private Long owner;

    @Column(name = "solde")
    private BigDecimal solde;

    public Comptes() {

    }

    public Comptes(Long owner, BigDecimal solde) {
        this.owner = owner;
        this.solde = solde;
    }

    // Getters et setters

    public void add(BigDecimal amount) {
        this.solde = this.solde.add(amount);
    }

    public void subtract(BigDecimal amount) {
        this.solde = this.solde.subtract(amount);
    }

    public int compareTo(Comptes otherAccount) {
        return this.solde.compareTo(otherAccount.getSolde());
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde( BigDecimal solde) {
        this.solde = solde;
    }

}
