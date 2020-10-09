package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MathsResultsActivity extends AppCompatActivity {

    private String choixCalcul;
    private int nbErreurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_results);
        nbErreurs = getIntent().getIntExtra("nbErreurs", 0);
        afficherResultats();
    }

    private void afficherResultats() {
        choixCalcul = getIntent().getStringExtra("choixCalculs");
        TextView textCategorie = findViewById(R.id.nomTypeCalcul);
        switch(choixCalcul){
            case "division":
                textCategorie.setText("Catégorie : Division");
                break;

            case "addition":
                textCategorie.setText("Catégorie : Addition");
                break;

            case "soustraction":
                textCategorie.setText("Catégorie : Soustraction");
                break;

            case "multiplication":
                textCategorie.setText("Catégorie : Multiplication");
                break;
        }

        TextView textErreur = findViewById(R.id.nbErreurs);
        textErreur.setText(Integer.toString(nbErreurs) + " erreurs");

    }

    public void restartMaths(View view) {
        Intent intent = new Intent(this, MathsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("choixCalculs", choixCalcul);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MathsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("choixCalculs", choixCalcul);
        startActivity(intent);
    }

    public void exitMaths(View view){
        Intent intent = new Intent(this, ChoixExerciceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}