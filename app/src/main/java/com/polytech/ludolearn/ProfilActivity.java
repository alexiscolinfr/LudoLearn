package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.polytech.ludolearn.database.Profil;

public class ProfilActivity extends AppCompatActivity {

    Profil profil;
    EditText editText_firstName, editText_lastName, editText_password1, editText_password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        setTitle(R.string.profil);

        profil = ConnexionActivity.profil;

        editText_firstName = findViewById(R.id.editText_firstName);
        editText_lastName = findViewById(R.id.editText_lastName);
        editText_password1 = findViewById(R.id.editText_password1);
        editText_password2 = findViewById(R.id.editText_password2);

        editText_firstName.setText(profil.getPrenom());
        editText_lastName.setText(profil.getNom());
    }

    public void save(View view){
        boolean error, changeInformations, changePassword;
        error = changeInformations = changePassword = false;

        String newFirstName = editText_firstName.getText().toString();
        String newLastName = editText_lastName.getText().toString();
        String newPassword1 = editText_password1.getText().toString();
        String newPassword2 = editText_password2.getText().toString();

        String password = profil.getMotDePasse();
        String firstName = profil.getPrenom();
        String lastName = profil.getNom();

        if(!newFirstName.equals(firstName) || !newLastName.equals(lastName)){
            if(!newFirstName.isEmpty() && !newLastName.isEmpty()){
                changeInformations=true;
            }else {
                error=true;
                Toast.makeText(view.getContext(), "Un champ est vide", Toast.LENGTH_SHORT).show();
            }
        }

        if((!newPassword1.isEmpty() || !newPassword2.isEmpty()) && !error){
            if(!newPassword1.isEmpty() && !newPassword2.isEmpty()){
                if(newPassword1.equals(newPassword2)){
                    if(!newPassword1.equals(password)){
                        changePassword=true;
                    }else {
                        error=true;
                        Toast.makeText(view.getContext(), "Mot de passe invalide", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    error=true;
                    Toast.makeText(view.getContext(), "Les mots de passe ne sont pas identiques", Toast.LENGTH_SHORT).show();
                }
            }else {
                error=true;
                Toast.makeText(view.getContext(), "Un champ est vide", Toast.LENGTH_SHORT).show();
            }
        }

        if (!error){
            if (changeInformations){
                profil.setPrenom(newFirstName);
                profil.setNom(newLastName);
            }
            if (changePassword){
                profil.setMotDePasse(newPassword1);
            }
            profil.save();
            ConnexionActivity.profil = profil;
        }
    }
}