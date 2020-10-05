package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuizResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        TextView textScore = (TextView) findViewById(R.id.textViewErreursAnglais);
        int nbQuestions = QuizActivity.nbQuestionsAnglais;
        int nbErreurs = nbQuestions - QuizActivity.nbErreursAnglais;
        textScore.setText(textScore.getText() + " : " + Integer.toString(nbErreurs) +"/"+ Integer.toString(nbQuestions));
        textScore = (TextView) findViewById(R.id.textViewErreursArtsPlastiques);
        nbQuestions = QuizActivity.nbQuestionsArtsPlastiques;
        nbErreurs = nbQuestions - QuizActivity.nbErreursArtsPlastiques;
        textScore.setText(textScore.getText() + " : " + Integer.toString(nbErreurs) +"/"+ Integer.toString(nbQuestions));
        textScore = (TextView) findViewById(R.id.textViewErreursFrancais);
        nbQuestions = QuizActivity.nbQuestionsFrancais;
        nbErreurs = nbQuestions - QuizActivity.nbErreursFrancais;
        textScore.setText(textScore.getText() + " : " + Integer.toString(nbErreurs) +"/"+ Integer.toString(nbQuestions));
        textScore = (TextView) findViewById(R.id.textViewErreursHistoireGeo);
        nbQuestions = QuizActivity.nbQuestionsHistoireGeo;
        nbErreurs = nbQuestions - QuizActivity.nbErreursHistoireGeo;
        textScore.setText(textScore.getText() + " : " + Integer.toString(nbErreurs) +"/"+ Integer.toString(nbQuestions));
        textScore = (TextView) findViewById(R.id.textViewErreursMaths);
        nbQuestions = QuizActivity.nbQuestionsMaths;
        nbErreurs = nbQuestions - QuizActivity.nbErreursMaths;
        textScore.setText(textScore.getText() + " : " + Integer.toString(nbErreurs) +"/"+ Integer.toString(nbQuestions));
        textScore = (TextView) findViewById(R.id.textViewErreursSciences);
        nbQuestions = QuizActivity.nbQuestionsSciences;
        nbErreurs = nbQuestions - QuizActivity.nbErreursSciences;
        textScore.setText(textScore.getText() + " : " + Integer.toString(nbErreurs) +"/"+ Integer.toString(nbQuestions));
        textScore = (TextView) findViewById(R.id.textViewErreursSport);
        nbQuestions = QuizActivity.nbQuestionsSport;
        nbErreurs = nbQuestions - QuizActivity.nbErreursSport;
        textScore.setText(textScore.getText() + " : " + Integer.toString(nbErreurs) +"/"+ Integer.toString(nbQuestions));
    }

    public void exitQuiz(View view){
        Intent intent = new Intent(this, ChoixExerciceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void restartQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}