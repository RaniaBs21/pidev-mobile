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
public class Event {
    int id,nbPlace;
    String titre,cat,desc,address,region,date;

    public Event() {
    }

    public Event(int id, int nbPlace, String titre, String cat, String desc, String address, String region, String date) {
        this.id = id;
        this.nbPlace = nbPlace;
        this.titre = titre;
        this.cat = cat;
        this.desc = desc;
        this.address = address;
        this.region = region;
        this.date = date;
    }

    public Event(int nbPlace, String titre, String cat, String desc, String address, String region, String date) {
        this.nbPlace = nbPlace;
        this.titre = titre;
        this.cat = cat;
        this.desc = desc;
        this.address = address;
        this.region = region;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", nbPlace=" + nbPlace + ", titre=" + titre + ", cat=" + cat + ", desc=" + desc + ", address=" + address + ", region=" + region + ", date=" + date + '}';
    }
    
}
