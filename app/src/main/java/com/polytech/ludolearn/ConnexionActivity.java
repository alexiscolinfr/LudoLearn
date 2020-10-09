package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.polytech.ludolearn.database.Profil;

import java.util.List;

public class ConnexionActivity extends AppCompatActivity {

    public boolean existe;
    public static String nomUser = null;
    public static Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        existe = false;
    }

    public void signIn(View view){

        RadioButton buttonStudent = findViewById(R.id.radioButtonStudent);
        RadioButton buttonTeacher = findViewById(R.id.radioButtonTeacher);
        EditText mail = findViewById(R.id.editTextEmail);
        EditText mdp = findViewById(R.id.editTextPassword);

        String valMail, valMdp;

        valMail = mail.getText().toString();
        valMdp = mdp.getText().toString();

        if (valMail.equals("") || valMdp.equals("")) {
            Toast.makeText(this, "Vous devez renseigner tous les champs !", Toast.LENGTH_SHORT).show();
        }
        else{
            if(buttonStudent.isChecked()) // CONNEXION D'UN ELEVE
            {
                List<Profil> liste = Profil.listAll(Profil.class);

                for (int i = 0; i<liste.size(); i++ ) {
                    if (liste.get(i).getAdresseMail().equals(valMail) && liste.get(i).getMotDePasse().equals(valMdp)) {
                        if(!liste.get(i).isTeacher()){
                            nomUser = liste.get(i).getPrenom() ;
                            photo = liste.get(i).getPhoto(this);
                            Intent intent = new Intent(this, ChoixExerciceActivity.class);
                            startActivity(intent);
                            existe = true;
                        }
                        break;
                    }
                }
                if (!existe){
                    Toast.makeText(this, "Erreur dans la saisie des informations !", Toast.LENGTH_SHORT).show();
                }
            }
            else if (buttonTeacher.isChecked()) // CONNEXION D'UN PROFESSEUR
            {
                List<Profil> liste = Profil.listAll(Profil.class);

                for (int i = 0; i<liste.size(); i++ ) {
                    if (liste.get(i).getAdresseMail().equals(valMail) && liste.get(i).getMotDePasse().equals(valMdp)) {
                        if(liste.get(i).isTeacher()){
                            nomUser = liste.get(i).getPrenom() ;
                            photo = liste.get(i).getPhoto(this);
                            Intent intent = new Intent(this, ProgressionActivity.class);
                            startActivity(intent);
                            existe = true;
                        }
                        break;
                    }
                }
                if (!existe){
                    Toast.makeText(this, "Erreur dans la saisie des informations !", Toast.LENGTH_SHORT).show();
                }
            }
            existe = false;
        }
    }
}