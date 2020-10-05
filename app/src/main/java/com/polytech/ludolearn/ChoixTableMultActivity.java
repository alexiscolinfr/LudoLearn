package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class ChoixTableMultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_table_mult);

        NumberPicker choixNum = (NumberPicker) findViewById(R.id.table);
        choixNum.setMinValue(0);
        choixNum.setMaxValue(10);
    }

    public void validate(View view){
        NumberPicker choixNum = (NumberPicker) findViewById(R.id.table);
        Intent intent = new Intent(this,MathsActivity.class);
        intent.putExtra("choixCalculs", "multiplication");
        intent.putExtra("table", choixNum.getValue());
        startActivity(intent);
    }
}