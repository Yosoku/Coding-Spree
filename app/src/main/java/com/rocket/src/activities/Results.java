package com.rocket.src.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.rocket.src.R;
import com.rocket.src.quiz.QuizGame;
import com.rocket.src.viewcomponents.ResultData;

public class Results extends AppCompatActivity implements View.OnClickListener {
    private TextView greetingView,wrongView,correctView,levelView,
            percentileView,summaryView;
    private ImageView image;
    private Button backButton;
    private QuizGame game;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        game = (QuizGame) getIntent().getSerializableExtra(
               "Result");
        float score = game.calculateResults();
        ResultData data = ResultData.FromPercentile(score);
        data.NAME = (String) getIntent().getExtras().get("Name");
        data.PERCENTILE = score;
        initComponents(data);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, Homepage.class));
    }

    private void initComponents(ResultData data){
        greetingView = findViewById(R.id.res_greeting);
        wrongView = findViewById(R.id.res_wrong_answers);
        correctView = findViewById(R.id.res_correct_answers);
        percentileView = findViewById(R.id.res_percentile);
        levelView = findViewById(R.id.res_level);
        summaryView = findViewById(R.id.res_summary);
        backButton = findViewById(R.id.home_button);
        backButton.setOnClickListener(this);
        image = findViewById(R.id.level_image);

        image.setImageResource(data.IMAGEID);
        greetingView.setText(getString(R.string.res_greeting,data.GREETING,
                data.NAME));
        wrongView.setText(getString(R.string.res_wrong_answers,game.WRONG));
        correctView.setText(getString(R.string.res_correct_answers,game.CORRECT));
        percentileView.setText(getString(R.string.res_percentile,data.PERCENTILE));
        levelView.setText(getString(R.string.res_level,data.LEVEL));
        summaryView.setText(getString(R.string.res_summary,data.SUMMARY));
        }

}