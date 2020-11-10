package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProgressionEleveActivity extends AppCompatActivity {

    String adresseMail,prenomNom;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_progression_eleve);
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        adresseMail = extras.getString("adresseMail");
        prenomNom = extras.getString("prenom_nom");

        setTitle("Résultats par élève");

        TextView textViewName = findViewById(R.id.textViewName);
        textViewName.setText("Élève : " + prenomNom);
    }

    public void quizResults(View view){
        Intent intent = new Intent(this, RadarChartActivity.class);
        intent.putExtra("adresseMail",adresseMail);
        intent.putExtra("jeu","Quiz");
        startActivity(intent);
    }

    public void mathsResults(View view){
        Intent intent = new Intent(this, RadarChartActivity.class);
        intent.putExtra("adresseMail",adresseMail);
        intent.putExtra("jeu","Maths");
        startActivity(intent);
    }

    public void intrusResults(View view){
        Intent intent = new Intent(this, MultiLineChartActivity.class);
        intent.putExtra("adresseMail",adresseMail);
        intent.putExtra("jeu","Intrus");
        startActivity(intent);
    }

    public void penduResults(View view){
        Intent intent = new Intent(this, MultiLineChartActivity.class);
        intent.putExtra("adresseMail",adresseMail);
        intent.putExtra("jeu","Pendu");
        startActivity(intent);
    }
}