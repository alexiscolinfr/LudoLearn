package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IntrusResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intrus_results);

        TextView textScore = findViewById(R.id.nbErreurs);
        int nbErreurs = IntrusActivity.nbErreurs;
        int resultat = 10-nbErreurs;
        textScore.setText(textScore.getText() + Integer.toString(resultat) +"/10");
    }

    public void exitIntrus(View view){
        Intent intent = new Intent(this, ChoixExerciceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void restartIntrus(View view) {
        Intent intent = new Intent(this, IntrusActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}