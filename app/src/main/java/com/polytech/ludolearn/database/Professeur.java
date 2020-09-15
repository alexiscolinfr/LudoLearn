package com.polytech.ludolearn.database;

import java.util.ArrayList;

public class Professeur extends Eleve {

    private ArrayList<Eleve> listeEleves;
    private String nomClasse;

    public Professeur(String nom, String prenom, String mail, String mdp, String classe)
    {
        super(nom, prenom, mail, mdp);
        this.nomClasse = classe;
    }

    public void addEleve(Eleve eleve){
        listeEleves.add(eleve);
    }
}
