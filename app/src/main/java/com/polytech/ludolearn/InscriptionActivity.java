package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.orm.SugarContext;
import com.polytech.ludolearn.database.Eleve;

import java.util.ArrayList;
import java.util.List;

public class InscriptionActivity extends AppCompatActivity {

    public Bitmap photo;
    public ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
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
        EditText nom = (EditText) findViewById(R.id.editTextFirstName);
        EditText prenom = (EditText) findViewById(R.id.editTextLastName);
        EditText mail = (EditText) findViewById(R.id.editTextEmail);
        EditText mdp = (EditText) findViewById(R.id.editTextPassword);

        String valNom, valPrenom, valMail, valMdp;

        valNom = nom.getText().toString();
        valPrenom = prenom.getText().toString();
        valMail = mail.getText().toString();
        valMdp = mdp.getText().toString();

        List<Eleve> listeInscrits = Eleve.listAll(Eleve.class);
        ArrayList<String> listeInscritsMail = new ArrayList<>();
        for (Eleve eleve : listeInscrits){
            listeInscritsMail.add(eleve.getAdresseMail());
        }

        if (valNom.equals("") || valPrenom.equals("") || valMail.equals("") || valMdp.equals("")) {
            Toast.makeText(this, "Vous devez renseigner tous les champs !", Toast.LENGTH_SHORT).show();
        } else if (listeInscritsMail.contains(valMail)){
            Toast.makeText(this, "Cette adresse mail existe déjà !", Toast.LENGTH_SHORT).show();
        } else {
            Eleve eleve = new Eleve(valNom, valPrenom, valMail, valMdp);
            eleve.save();
            eleve.setPhoto(photo, this);
            eleve.save();
            Intent intent = new Intent(this, ConnexionActivity.class);
            startActivity(intent);
        }
    }
}