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

    private boolean existe;
    public static Profil profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        setTitle(R.string.sign_in);
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
            // Connexion d'un élève
            if (buttonStudent.isChecked()) {
                List<Profil> liste = Profil.listAll(Profil.class);

                for (int i = 0 ; i < liste.size() ; i++ ) {
                    if (liste.get(i).getAdresseMail().equals(valMail) && liste.get(i).getMotDePasse().equals(valMdp)) {
                        if (!liste.get(i).isTeacher()) {
                            profil = liste.get(i);
                            Intent intent = new Intent(this, ChoixExerciceActivity.class);
                            startActivity(intent);
                            existe = true;
                        }
                        break;
                    }
                }
                if (!existe) {
                    Toast.makeText(this, "Erreur dans la saisie des informations !", Toast.LENGTH_SHORT).show();
                }
            }
            // Connexion d'un professeur
            else if (buttonTeacher.isChecked()) {
                List<Profil> liste = Profil.listAll(Profil.class);

                for (int i = 0 ; i < liste.size() ; i++ ) {
                    if (liste.get(i).getAdresseMail().equals(valMail) && liste.get(i).getMotDePasse().equals(valMdp)) {
                        if (liste.get(i).isTeacher()) {
                            profil = liste.get(i);
                            Intent intent = new Intent(this, ProgressionActivity.class);
                            startActivity(intent);
                            existe = true;
                        }
                        break;
                    }
                }
                if (!existe) {
                    Toast.makeText(this, "Erreur dans la saisie des informations !", Toast.LENGTH_SHORT).show();
                }
            }
            existe = false;
        }
    }
}