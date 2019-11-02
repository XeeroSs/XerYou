package com.app.xeross.infoxer.View;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ItemClassement {

    private String classement;
    private int image;
    private String title;
    private String statut;
    private int color;

    public ItemClassement(String classement, int image, String title, String statut, int color) {
        this.classement = classement;
        this.image = image;
        this.title = title;
        this.statut = statut;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getClassement() {
        return classement;
    }

    public void setClassement(String classement) {
        this.classement = classement;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public JSONArray convertToJSONArray() {

        List laListe = new ArrayList();
        laListe.add(classement);
        laListe.add(image);
        laListe.add(title);
        laListe.add(statut);
        laListe.add(color);

        return new JSONArray(laListe);
    }
}
