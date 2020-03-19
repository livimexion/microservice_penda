package com.isi.msamamependacissem2gl.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.javafx.beans.IDProperty;
import org.hibernate.validator.constraints.Length;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

/**
 * auteur:Mame Penda Cisse
 * classe:M2GL
 */
//@JsonFilter("monFiltreDynamique")
@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;

    @Length(min = 3, max = 20, message = "le Nom doit etre compris entre 3 et 20 caracteres. Veuillez mettre un nom correct")
    private String nom;

    @Min(value = 1)
    private int prix;

    private int prixAchat;
    @Transient
    private int marge;



    public int getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    public Product() {
    }

    public Product(@Length(min = 3, max = 20, message = "le Nom doit etre compris entre 3 et 20 caracteres. Veuillez mettre un nom correct") String nom, @Min(value = 1) int prix, int prixAchat, int marge) {
        this.nom = nom;
        this.prix = prix;
        this.prixAchat = prixAchat;
        this.marge = marge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    public int getMarge() {
        return marge;
    }

    public void setMarge(int marge) {
        this.marge = this.prix - this.getPrixAchat();
    }
}
