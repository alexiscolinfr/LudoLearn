package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.polytech.ludolearn.database.Profil;

public class ChoixExerciceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_exercice);

        TextView text = findViewById(R.id.textViewName);
        ImageView img = findViewById(R.id.imageViewProfilePicture);

        Profil profil = ConnexionActivity.profil;

        text.setText(text.getText() + " " + profil.getPrenom() + " !");

        if (profil.getPhoto(this) != null) {
            img.setImageBitmap(profil.getPhoto(this));
        }
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