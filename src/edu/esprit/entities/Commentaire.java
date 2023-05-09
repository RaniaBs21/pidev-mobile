/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author 
 */
public class Commentaire {
    int id,nbLike,nbDislike,idPost;
    String date,contenu;

    public Commentaire() {
    }

    public Commentaire(int id, int nbLike, int nbDislike, int idPost, String date, String contenu) {
        this.id = id;
        this.nbLike = nbLike;
        this.nbDislike = nbDislike;
        this.idPost = idPost;
        this.date = date;
        this.contenu = contenu;
    }

    public Commentaire(int nbLike, int nbDislike, int idPost, String date, String contenu) {
        this.nbLike = nbLike;
        this.nbDislike = nbDislike;
        this.idPost = idPost;
        this.date = date;
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbLike() {
        return nbLike;
    }

    public void setNbLike(int nbLike) {
        this.nbLike = nbLike;
    }

    public int getNbDislike() {
        return nbDislike;
    }

    public void setNbDislike(int nbDislike) {
        this.nbDislike = nbDislike;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "nbLike=" + nbLike + ", nbDislike=" + nbDislike + ", idPost=" + idPost + ", date=" + date + ", contenu=" + contenu + '}';
    }
    
    
}
//llllllllll