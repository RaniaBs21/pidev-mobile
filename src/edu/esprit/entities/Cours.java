/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author Hedi
 */
public class Cours {
    int idCours,niveau;
    float prix;
    String titre,cat,sousCat,desc,date;

    public Cours() {
    }

    public Cours(int idCours, int niveau, float prix, String cat, String titre, String sousCat, String desc, String date) {
        this.idCours = idCours;
        this.niveau = niveau;
        this.prix = prix;
        this.titre = titre;
        this.cat=cat;
        this.sousCat = sousCat;
        this.desc = desc;
        this.date = date;
    }

    public Cours(int niveau, float prix, String titre, String cat, String sousCat, String desc, String date) {
        this.niveau = niveau;
        this.prix = prix;
        this.titre = titre;
        this.cat=cat;
        this.sousCat = sousCat;
        this.desc = desc;
        this.date = date;
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSousCat() {
        return sousCat;
    }

    public void setSousCat(String sousCat) {
        this.sousCat = sousCat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "Cours{" + "idCours=" + idCours + ", niveau=" + niveau + ", prix=" + prix + ", titre=" + titre + ", sousCat=" + sousCat + ", desc=" + desc + ", date=" + date + '}';
    }
       
}
