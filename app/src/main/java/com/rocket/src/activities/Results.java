package com.rocket.src.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rocket.src.R;
import com.rocket.src.quiz.QuizGame;
import com.rocket.src.viewcomponents.RecyclerAdapter;

public class Results extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private TextView scoreView;
    private Button backButton;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        QuizGame results = (QuizGame) getIntent().getSerializableExtra("Result");
        backButton = findViewById(R.id.result_screen_back_button);
        backButton.setOnClickListener(this);
        scoreView = ((TextView) findViewById(R.id.resultTextView));
        scoreView.setText(String.valueOf(results.calculateResults()));
        recyclerView = findViewById(R.id.rv_results);
        RecyclerAdapter adapter =
                new RecyclerAdapter(results.getScoreAnswers());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, Homepage.class));
    }


}