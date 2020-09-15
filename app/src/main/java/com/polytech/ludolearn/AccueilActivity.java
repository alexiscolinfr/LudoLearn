package com.polytech.ludolearn;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Intent intent = new Intent(AccueilActivity.this, BackgroundSoundService.class);
        startService(intent);
    }

    public void onPlayClicked(View view){
        Intent intent = new Intent(this, ChoixConnectionActivity.class);
        startActivity(intent);
    }

    public void onSoundClicked(View view){
        ImageButton img = (ImageButton) findViewById(R.id.sound_button);

        if (isMyServiceRunning(BackgroundSoundService.class)) {
            stopService(new Intent(this, BackgroundSoundService.class));
            img.setImageResource(R.drawable.volume_mute);
        } else {
            startService(new Intent(this, BackgroundSoundService.class));
            img.setImageResource(R.drawable.volume_up);
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}