package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.polytech.ludolearn.database.Profil;

import java.util.ArrayList;
import java.util.List;

public class InscriptionActivity extends AppCompatActivity {

    public Bitmap photo;
    public ImageView img;
    private boolean isTeacher = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_eleve);
        Bundle extras = getIntent().getExtras();
        isTeacher = extras.getBoolean("isTeacher");
    }

    public void takePicture(View view){
        Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photo= (Bitmap) data.getExtras().get("data");
        img=(ImageView) findViewById(R.id.imageView);
        img.setImageBitmap(photo);
    }

    public void validate (View view){
        EditText nom = (EditText) findViewById(R.id.editTextLastName);
        EditText prenom = (EditText) findViewById(R.id.editTextFirstName);
        EditText codeClasse = (EditText) findViewById(R.id.editTextCodeClasse);
        EditText mail = (EditText) findViewById(R.id.editTextEmail);
        EditText mdp = (EditText) findViewById(R.id.editTextPassword);

        String valNom, valPrenom, valCodeClasse, valMail, valMdp;

        valNom = nom.getText().toString();
        valPrenom = prenom.getText().toString();
        valCodeClasse = codeClasse.getText().toString();
        valMail = mail.getText().toString();
        valMdp = mdp.getText().toString();

        List<Profil> listeInscrits = Profil.listAll(Profil.class);
        ArrayList<String> listeInscritsMail = new ArrayList<>();
        ArrayList<Integer> listeCodesClasse = new ArrayList<>();
        for (Profil profil : listeInscrits){
            listeInscritsMail.add(profil.getAdresseMail());
            if(!listeCodesClasse.contains(profil.getCodeClasse()))
                listeCodesClasse.add(profil.getCodeClasse());
        }

        if (valNom.equals("") || valPrenom.equals("") || valCodeClasse.equals("") || valMail.equals("") || valMdp.equals("")) {
            Toast.makeText(this, "Vous devez renseigner tous les champs !", Toast.LENGTH_SHORT).show();
        } else if (listeInscritsMail.contains(valMail)){
                Toast.makeText(this, "Cette adresse mail existe déjà !", Toast.LENGTH_SHORT).show();
        } else if (!isTeacher && !listeCodesClasse.contains(Integer.parseInt(valCodeClasse))) {
            Toast.makeText(this, "Le code classe est invalide !", Toast.LENGTH_SHORT).show();
        } else if (isTeacher && listeCodesClasse.contains(Integer.parseInt(valCodeClasse))) {
            Toast.makeText(this, "Le code classe est invalide !", Toast.LENGTH_SHORT).show();
        }else {
            Profil profil = new Profil(valNom, valPrenom, Integer.parseInt(valCodeClasse), valMail, valMdp, isTeacher);
            profil.save();
            profil.setPhoto(photo, this);
            profil.save();
            finish();
        }
    }
}