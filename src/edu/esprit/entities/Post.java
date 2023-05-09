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
public class Post {
    int idPost,nbComm;
    String desc,image,date;

    public Post() {
    }

    public Post(int idPost, int nbComm, String desc, String image, String date) {
        this.idPost = idPost;
        this.nbComm = nbComm;
        this.desc = desc;
        this.image = image;
        this.date = date;
    }

    public Post(int nbComm, String desc, String image, String date) {
        this.nbComm = nbComm;
        this.desc = desc;
        this.image = image;
        this.date = date;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getNbComm() {
        return nbComm;
    }

    public void setNbComm(int nbComm) {
        this.nbComm = nbComm;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" + "idPost=" + idPost + ", nbComm=" + nbComm + ", desc=" + desc + ", image=" + image + ", date=" + date + '}';
    }
    
}
