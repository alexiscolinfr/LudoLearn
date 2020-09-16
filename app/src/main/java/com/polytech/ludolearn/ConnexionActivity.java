package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.polytech.ludolearn.database.Eleve;

import java.util.List;

public class ConnexionActivity extends AppCompatActivity {

    public boolean existe = false;
    public static String nomUser = null;
    public static Bitmap photo;
    public static int progression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
    }

    public void signIn(View view){

        EditText mail = (EditText) findViewById(R.id.editTextEmail);
        EditText mdp = (EditText) findViewById(R.id.editTextPassword);

        String valMail, valMdp;

        valMail = mail.getText().toString();
        valMdp = mdp.getText().toString();

        if (valMail.equals("") || valMdp.equals("")) {
            Toast.makeText(this, "Vous devez renseigner tous les champs !", Toast.LENGTH_SHORT).show();
        }

        List<Eleve> liste = Eleve.listAll(Eleve.class);

        for (int i = 0; i<liste.size(); i++ ) {
            if (liste.get(i).getAdresseMail().equals(valMail) && liste.get(i).getMotDePasse().equals(valMdp)) {
                nomUser = liste.get(i).getPrenom() ;
                photo = liste.get(i).getPhoto(this);
                progression = liste.get(i).getProgression();
                Intent intent = new Intent(this, ChoixExerciceActivity.class);
                startActivity(intent);
                existe = true;
            }
        }
        if (!existe){
            Toast.makeText(this, "Erreur dans la saisie des informations !", Toast.LENGTH_SHORT).show();
        }

    }

    public void signUp(View view){
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }
}