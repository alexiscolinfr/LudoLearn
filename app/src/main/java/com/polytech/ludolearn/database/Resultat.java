package com.polytech.ludolearn.database;

import com.orm.SugarRecord;

public class Resultat extends SugarRecord {

    private String adresseMail;
    private String jeu;
    private String categorie;
    private Integer note;

    public Resultat(){}

    public Resultat(String adresseMail, String jeu, String categorie, int note){
        this.adresseMail = adresseMail;
        this.jeu = jeu;
        this.categorie = categorie;
        this.note = note;
    }

    public String getCategorie(){ return categorie; }

    public Integer getNote(){ return note; }

}
