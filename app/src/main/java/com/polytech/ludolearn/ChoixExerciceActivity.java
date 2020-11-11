package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.polytech.ludolearn.database.Profil;

public class ChoixExerciceActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_exercice);
        setTitle(R.string.game_choice);

        TextView text = findViewById(R.id.textViewName);
        ImageButton img = findViewById(R.id.imageButtonProfilePicture);

        Profil profil = ConnexionActivity.profil;

        text.setText(text.getText() + " " + profil.getPrenom() + " !");

        if (profil.getPhoto(this) != null) {
            img.setImageBitmap(profil.getPhoto(this));
        }
    }

    public void openProfil(View view){
        Intent intent = new Intent(this, ProfilActivity.class);
        startActivity(intent);
    }

    public void logOut(View view){
        Intent intent = new Intent(this, AccueilActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void playMaths (View view) {
        Intent intent = new Intent(this, ChoixMathsActivity.class);
        startActivity(intent);
    }

    public void playQuiz (View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    public void playPendu (View view) {
        Intent intent = new Intent(this, PenduActivity.class);
        startActivity(intent);
    }

    public void playIntrus (View view) {
        Intent intent = new Intent(this, IntrusActivity.class);
        startActivity(intent);
    }
}