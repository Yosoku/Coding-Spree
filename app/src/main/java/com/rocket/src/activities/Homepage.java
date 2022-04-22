package com.rocket.src.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rocket.src.R;


public class Homepage extends AppCompatActivity implements View.OnClickListener {
    private Button playButton;
    private Button profileButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("OnCreate","Constructor called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton =  findViewById(R.id.play_button);
        profileButton =  findViewById(R.id.profile_button);
        exitButton =  findViewById(R.id.exit_button);
        // Setting up listeners
        playButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Log.d("OnClickLALA",view.toString());
        if (view.equals(playButton)) playButtonClicked();
        if (view.equals(profileButton)) profileButtonClicked();
        if (view.equals(exitButton)) exitButtonClicked();
    }

    private void playButtonClicked() {
        startActivity(new Intent(this,LanguageSelection.class));
    }

    private void profileButtonClicked() {
        startActivity(new Intent(this,Profile.class));
    }

    private void exitButtonClicked() {
        finishAffinity();
    }
}