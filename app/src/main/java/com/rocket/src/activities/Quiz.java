package com.rocket.src.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.rocket.src.R;

public class Quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        String language = (String) getIntent().getExtras().get("Language");
        ((TextView)findViewById(R.id.languageSelected)).setText(language);
    }
}