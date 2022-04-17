package com.rocket.src;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Homepage extends AppCompatActivity implements View.OnClickListener {
    private Button playButton;
    private Button profileButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = ((Button) findViewById(R.id.play_button));
        profileButton = ((Button) findViewById(R.id.profile_button));
        exitButton = ((Button) findViewById(R.id.exit_button));
        // Setting up listeners
        playButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == null) return;
        if (view.equals(playButton)) playButtonClicked();
        if (view.equals(profileButton)) profileButtonClicked();
        if (view.equals(exitButton)) exitButtonClicked();
    }

    private void playButtonClicked() {
    }

    private void profileButtonClicked() {
    }

    private void exitButtonClicked() {
        finishAndRemoveTask();
    }
}