package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.polytech.ludolearn.database.Profil;
import com.polytech.ludolearn.database.Resultat;

public class QuizResultsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        setTitle(R.string.quiz_game);

        Profil profil = ConnexionActivity.profil;

        // Affichage du score en Anglais
        TextView textScore = findViewById(R.id.textViewErreursAnglais);
        int nbQuestions = QuizActivity.nbQuestionsAnglais;
        int nbReponseCorrecte = nbQuestions - QuizActivity.nbErreursAnglais;
        textScore.setText(textScore.getText() + " : " + nbReponseCorrecte + "/" + nbQuestions);
        if(nbQuestions>0){
           int note = (10*nbReponseCorrecte)/nbQuestions;
            Resultat resultatAnglais = new Resultat(profil.getAdresseMail(),"Quiz","Anglais", note);
            resultatAnglais.save();
        }

        // Affichage du score en Français
        textScore = findViewById(R.id.textViewErreursFrancais);
        nbQuestions = QuizActivity.nbQuestionsFrancais;
        nbReponseCorrecte = nbQuestions - QuizActivity.nbErreursFrancais;
        textScore.setText(textScore.getText() + " : " + nbReponseCorrecte + "/" + nbQuestions);
        if(nbQuestions>0){
            int note = (10*nbReponseCorrecte)/nbQuestions;
            Resultat resultatFrancais = new Resultat(profil.getAdresseMail(),"Quiz","Français", note);
            resultatFrancais.save();
        }

        // Affichage du score en Histoire
        textScore = findViewById(R.id.textViewErreursHistoire);
        nbQuestions = QuizActivity.nbQuestionsHistoire;
        nbReponseCorrecte = nbQuestions - QuizActivity.nbErreursHistoire;
        textScore.setText(textScore.getText() + " : " + nbReponseCorrecte + "/" + nbQuestions);
        if(nbQuestions>0){
            int note = (10*nbReponseCorrecte)/nbQuestions;
            Resultat resultatHistoire = new Resultat(profil.getAdresseMail(),"Quiz","Histoire", note);
            resultatHistoire.save();
        }

        // Affichage du score en Géographie
        textScore = findViewById(R.id.textViewErreursGeographie);
        nbQuestions = QuizActivity.nbQuestionsGeographie;
        nbReponseCorrecte = nbQuestions - QuizActivity.nbErreursGeographie;
        textScore.setText(textScore.getText() + " : " + nbReponseCorrecte + "/" + nbQuestions);
        if(nbQuestions>0){
            int note = (10*nbReponseCorrecte)/nbQuestions;
            Resultat resultatGeographie = new Resultat(profil.getAdresseMail(),"Quiz","Géographie", note);
            resultatGeographie.save();
        }

        // Affichage du score en Mathématiques
        textScore = findViewById(R.id.textViewErreursMaths);
        nbQuestions = QuizActivity.nbQuestionsMaths;
        nbReponseCorrecte = nbQuestions - QuizActivity.nbErreursMaths;
        textScore.setText(textScore.getText() + " : " + nbReponseCorrecte + "/" + nbQuestions);
        if(nbQuestions>0){
            int note = (10*nbReponseCorrecte)/nbQuestions;
            Resultat resultatMaths = new Resultat(profil.getAdresseMail(),"Quiz","Mathématiques", note);
            resultatMaths.save();
        }

        // Affichage du score en Sciences
        textScore = findViewById(R.id.textViewErreursSciences);
        nbQuestions = QuizActivity.nbQuestionsSciences;
        nbReponseCorrecte = nbQuestions - QuizActivity.nbErreursSciences;
        textScore.setText(textScore.getText() + " : " + nbReponseCorrecte + "/" + nbQuestions);
        if(nbQuestions>0){
            int note = (10*nbReponseCorrecte)/nbQuestions;
            Resultat resultatSciences = new Resultat(profil.getAdresseMail(),"Quiz","Sciences", note);
            resultatSciences.save();
        }
    }

    public void exitQuiz(View view) {
        Intent intent = new Intent(this, ChoixExerciceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void restartQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, ChoixExerciceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}