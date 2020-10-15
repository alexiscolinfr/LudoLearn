package com.polytech.ludolearn.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.orm.SugarRecord;

import java.io.FileOutputStream;

public class Profil extends SugarRecord {

    private boolean isTeacher;
    private String nom;
    private String prenom;
    private int codeClasse;
    private String adresseMail;
    private String motDePasse;
    private String photoPath;

    public Profil(){}

    public Profil(String nom, String prenom, int codeClasse, String mail, String mdp, boolean isTeacher){
        this.isTeacher = isTeacher;
        this.nom = nom;
        this.prenom = prenom;
        this.codeClasse = codeClasse;
        this.adresseMail = mail;
        this.motDePasse = mdp;
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

    public boolean isTeacher() {
        return isTeacher;
    }

    public String getNom(){
        return nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public int getCodeClasse(){return  codeClasse;}

    public String getAdresseMail(){
        return adresseMail;
    }

    public String getMotDePasse(){
        return motDePasse;
    }

    public void setPhoto(Bitmap photo, Context context) {
        try {
            photoPath = getAdresseMail() + ".jpg";
            FileOutputStream out = context.openFileOutput(photoPath,  Context.MODE_PRIVATE);
            photo.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
