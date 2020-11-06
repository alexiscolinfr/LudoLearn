package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.utils.Utils;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.polytech.ludolearn.database.Profil;
import com.polytech.ludolearn.database.Resultat;

import java.util.List;

public class ProgressionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progression);
        Utils.init(this);
        setTitle("Résultats");

        ListView listView = (ListView) findViewById(R.id.listView);
        Profil profil = ConnexionActivity.profil;
        int codeClasse = profil.getCodeClasse();
        final List<Profil> profils = Select.from(Profil.class).where(Condition.prop("is_teacher").eq(0),Condition.prop("code_classe").eq(codeClasse)).list();
        final EleveAdapter adapter = new EleveAdapter(ProgressionActivity.this, profils);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Profil item = adapter.getItem(position);
                Intent intent = new Intent(ProgressionActivity.this,ProgressionEleveActivity.class);
                assert item != null;
                intent.putExtra("adresseMail",item.getAdresseMail());
                intent.putExtra("prenom_nom",item.getPrenom() + " " + item.getNom());
                startActivity(intent);
            }
        });
    }

    public void clearResults(View view){
        Resultat.deleteAll(Resultat.class);
        Toast.makeText(this, "Les résultats ont été supprimés", Toast.LENGTH_SHORT).show();
    }
}