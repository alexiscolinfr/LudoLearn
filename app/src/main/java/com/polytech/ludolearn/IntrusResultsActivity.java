package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.polytech.ludolearn.database.Profil;
import com.polytech.ludolearn.database.Resultat;

public class IntrusResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intrus_results);
        Profil profil = ConnexionActivity.profil;

        TextView textScore = findViewById(R.id.nbErreurs);
        int nbErreurs = IntrusActivity.nbErreurs;
        int note = 10 - nbErreurs;
        textScore.setText(textScore.getText() + Integer.toString(note) + "/10");

        Resultat resultatIntrus = new Resultat(profil.getAdresseMail(),"Intrus",null, note);
        resultatIntrus.save();
    }

    public void exitIntrus(View view) {
        Intent intent = new Intent(this, ChoixExerciceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void restartIntrus(View view) {
        Intent intent = new Intent(this, IntrusActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, ChoixExerciceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}