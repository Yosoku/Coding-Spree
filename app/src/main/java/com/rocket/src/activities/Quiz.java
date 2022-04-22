package com.rocket.src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rocket.src.R;
import com.rocket.src.quiz.Answer;
import com.rocket.src.quiz.QuizGame;
import com.rocket.src.quiz.QuizQuestion;
import com.rocket.src.viewcomponents.MarkdownWrapper;

import java.io.Serializable;

public class Quiz extends AppCompatActivity implements View.OnClickListener {
    public int kTurns;
    private int currTurn;
    private QuizQuestion currentQuestion;
    private Button btn_a, btn_b, btn_c, btn_d;

    private TextView txtView;
    private QuizGame quizGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        // Initialize all the views

        txtView = findViewById(R.id.text_view_question);
        btn_a = findViewById(R.id.btn_answer_a);
        btn_b = findViewById(R.id.btn_answer_b);
        btn_c = findViewById(R.id.btn_answer_c);
        btn_d = findViewById(R.id.btn_answer_d);
        String lang = (String) getIntent().getExtras().get("Language");
        Log.d("LANGUAGEGEGE", lang);
        quizGame = new QuizGame(lang, 4, this);
        currentQuestion = quizGame.nextQuestion();
        updateComponents();
        // Event listeners
        btn_a.setOnClickListener(this);
        btn_b.setOnClickListener(this);
        btn_c.setOnClickListener(this);
        btn_d.setOnClickListener(this);
    }

    private void updateComponents() {
        MarkdownWrapper.applyMarkdown(this, txtView,
                currentQuestion.getQuestion());
        btn_a.setText(currentQuestion.getAnswers().get(0).getAnswer());
        btn_b.setText(currentQuestion.getAnswers().get(1).getAnswer());
        btn_c.setText(currentQuestion.getAnswers().get(2).getAnswer());
        btn_d.setText(currentQuestion.getAnswers().get(3).getAnswer());
    }

    @Override
    public void onClick(View view) {
        Button clicked = (Button) view;
        quizGame.mapAnswerToQuestion(currentQuestion,
                new Answer(clicked.getText().toString())); // Adds answer to
        // result set
        currentQuestion = quizGame.nextQuestion();
        if (currentQuestion == null) // All questions were played
        {
            Intent intent = new Intent(this, Results.class); // Change this to Results.class later
            intent.putExtra("Result", (Serializable) quizGame);
            startActivity(intent);
        } else // Game continues
        {
            updateComponents();
        }
    }
}