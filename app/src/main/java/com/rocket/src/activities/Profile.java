package com.rocket.src.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rocket.src.R;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView tv =   findViewById(R.id.viewSourceTV);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==null) return;
        if(view.equals(backButton)) backButtonClicked();
    }

    private void backButtonClicked() {
        startActivity(new Intent(this,Homepage.class));
    }
}