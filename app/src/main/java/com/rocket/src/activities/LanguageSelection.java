package com.rocket.src.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rocket.src.R;

public class LanguageSelection extends AppCompatActivity implements View.OnClickListener {
    private Button javaButton,cppButton,pythonButton,backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);
        javaButton =  findViewById(R.id.java_selection_btn);
        cppButton = findViewById(R.id.cpp_selection_btn);
        pythonButton =  findViewById(R.id.python_selection_btn);
        backButton = findViewById(R.id.selection_back_button);
        // Event Listeners
        javaButton.setOnClickListener(this);
        cppButton.setOnClickListener(this);
        pythonButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        }

    @Override
    public void onClick(View view) {
        if(view.equals(backButton)){
            startActivity(new Intent(this,Homepage.class));
            return;
        }
        Button clicked = (Button)view;
        Intent intent = new Intent(this,Quiz.class);
        intent.putExtra("Language",clicked.getText());
        startActivity(intent);
    }
}