package com.polytech.ludolearn.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.orm.SugarRecord;

import java.io.FileOutputStream;

public class Eleve extends SugarRecord {

    private String nom;
    private String prenom;
    private String adresseMail;
    private String motDePasse;
    private String photoPath;
    private int progression;

    public Eleve(){}

    public Eleve(String nom, String prenom, String mail, String mdp){
        this.nom=nom;
        this.prenom=prenom;
        this.adresseMail=mail;
        this.motDePasse=mdp;
        this.progression=100;
    }

    public Bitmap getPhoto(Context context) {
        try{
            Bitmap bitmap = BitmapFactory.decodeStream(context.openFileInput(photoPath));
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getNom(){
        return nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public String getAdresseMail(){
        return adresseMail;
    }

    public String getMotDePasse(){
        return motDePasse;
    }

    public int getProgression() { return  progression; }

    public void setPhoto(Bitmap photo, Context context) {
        try{
            photoPath = getAdresseMail() + ".jpg";
            FileOutputStream out = context.openFileOutput(photoPath,  Context.MODE_PRIVATE);
            photo.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setPrenom(String prenom){
        this.prenom=prenom;
    }

    public void setNom(String nom){
        this.nom=nom;
    }

    public void setAdresseMail(String mail){
        this.adresseMail=mail;
    }

    public void setMotDePasse(String mdp){
        this.nom=mdp;
    }

    public  void setProgression(int progression) { this.progression=progression;}

}
