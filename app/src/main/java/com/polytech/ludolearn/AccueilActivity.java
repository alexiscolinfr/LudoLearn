package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AccueilActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    private boolean sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.background_music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        sound=true;
    }

    public void onPlayClicked(View view){

    }

    public void onSoundClicked(View view){
        ImageButton img = (ImageButton) findViewById(R.id.sound_button);

        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            img.setImageResource(R.drawable.volume_mute_solid);
            sound=false;
        } else {
            mediaPlayer.start();
            img.setImageResource(R.drawable.volume_up_solid);
            sound=true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sound){
            mediaPlayer.start();
        }
    }


}