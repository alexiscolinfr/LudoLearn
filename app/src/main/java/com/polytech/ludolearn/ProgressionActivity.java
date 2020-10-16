package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.polytech.ludolearn.database.Resultat;

import java.util.List;

public class ProgressionActivity extends AppCompatActivity {

    private ListView listView;
    private List<Resultat> resultats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progression);

        listView = (ListView)findViewById(R.id.listView);
        resultats = Resultat.listAll(Resultat.class);
        ResultatAdapter adapter = new ResultatAdapter (ProgressionActivity.this, resultats);
        listView.setAdapter(adapter);
    }

    public void clearResults(View view){
        Resultat.deleteAll(Resultat.class);
        resultats = Resultat.listAll(Resultat.class);
        ResultatAdapter adapter = new ResultatAdapter (ProgressionActivity.this, resultats);
        listView.setAdapter(adapter);
    }
}