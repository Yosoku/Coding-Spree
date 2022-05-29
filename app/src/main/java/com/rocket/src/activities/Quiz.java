package com.rocket.src.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.InputType;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.rocket.src.R;
import com.rocket.src.quiz.Answer;
import com.rocket.src.quiz.QuizGame;
import com.rocket.src.quiz.QuizQuestion;
import com.rocket.src.viewcomponents.MarkdownWrapper;

import java.util.HashMap;
import java.util.Objects;

public class Quiz extends AppCompatActivity implements View.OnClickListener {
    private QuizQuestion currentQuestion;
    private Button btn_a, btn_b, btn_c, btn_d, next_button;
    private TextView question_header, answer_a, answer_b, answer_c, answer_d;
    private TextView question;
    private QuizGame quizGame;
    private HashMap<Button, Pair<TextView, Answer>> buttonTextViewMap;
    private int questionCounter = 1;
    private String name;
    private MediaPlayer sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        sound = MediaPlayer.create(this,
                R.raw.correct_answer);
        // Initialize all the views
        buttonTextViewMap = new HashMap<Button, Pair<TextView, Answer>>();
        initComponents();
        String lang = (String) getIntent().getExtras().get("Language");
        int rounds = getResources().getInteger(R.integer.number_of_turns);
        quizGame = new QuizGame(lang, rounds, this);
        currentQuestion = quizGame.nextQuestion();
        updateComponents();
    }

    private void initComponents() {

        question = findViewById(R.id.text_view_question);
        btn_a = findViewById(R.id.btn_answer_a);
        btn_b = findViewById(R.id.btn_answer_b);
        btn_c = findViewById(R.id.btn_answer_c);
        btn_d = findViewById(R.id.btn_answer_d);
        next_button = findViewById(R.id.next_button);
        question_header = findViewById(R.id.text_view_question_header);
        answer_a = findViewById(R.id.text_view_answer_a);
        answer_b = findViewById(R.id.text_view_answer_b);
        answer_c = findViewById(R.id.text_view_answer_c);
        answer_d = findViewById(R.id.text_view_answer_d);
        // Event listeners
        btn_a.setOnClickListener(this);
        btn_b.setOnClickListener(this);
        btn_c.setOnClickListener(this);
        btn_d.setOnClickListener(this);
        next_button.setOnClickListener(this);
    }

    private void updateComponents() {
        // map all the buttons to their textViews,answers
        // pairing textview with an answer is helpful when mapping results
        buttonTextViewMap.put(btn_a, new Pair<>(answer_a,
                currentQuestion.getAnswers().get(0)));
        buttonTextViewMap.put(btn_b, new Pair<>(answer_b,
                currentQuestion.getAnswers().get(1)));
        buttonTextViewMap.put(btn_c, new Pair<>(answer_c,
                currentQuestion.getAnswers().get(2)));
        buttonTextViewMap.put(btn_d, new Pair<>(answer_d,
                currentQuestion.getAnswers().get(3)));
        // Render markdown in the Textviews
        MarkdownWrapper.applyMarkdown(this, question,
                currentQuestion.getQuestion());
        MarkdownWrapper.applyMarkdown(this, answer_a,
                currentQuestion.getAnswers().get(0).getAnswer());
        MarkdownWrapper.applyMarkdown(this, answer_b,
                currentQuestion.getAnswers().get(1).getAnswer());
        MarkdownWrapper.applyMarkdown(this, answer_c,
                currentQuestion.getAnswers().get(2).getAnswer());
        MarkdownWrapper.applyMarkdown(this, answer_d,
                currentQuestion.getAnswers().get(3).getAnswer());
        // Question 1,2,3 etc
        String question = getString(R.string.question_number_header, questionCounter);
        question_header.setText(question);
        next_button.setVisibility(View.INVISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        Button clicked = (Button) view;
        // get clicked button
        // if its next then check for last queestion and start intent
        // if its answer log the answer and make next invisible
        // Loop through pairs or button-textview and show only the correct
        // answer
        if (clicked.equals(next_button)) {
            currentQuestion = quizGame.nextQuestion();
            updateColors(true);
            if (currentQuestion == null) // All questions were played
            {
                endGame();
            } else {
                // theres still questions left
                questionCounter++;
                updateComponents();
            }
        } else {// answer button clicked
            next_button.setVisibility(View.VISIBLE);
            // keep results
            Answer playerAnswer = Objects.requireNonNull(buttonTextViewMap.get(clicked)).second;
            if (playerAnswer != currentQuestion.getCorrectAnswer()) {
                // Get instance of Vibrator from current Context
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 400 milliseconds
                v.vibrate(400);
            }else
            {
                sound.start();
            }
            updateColors(false);
            quizGame.mapAnswerToQuestion(currentQuestion, playerAnswer);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateColors(boolean reset) {
        if (reset) {
            buttonTextViewMap.forEach((K, V) -> K.getBackground().setColorFilter(K.getContext().getResources().getColor(R.color.palette_orange), PorterDuff.Mode.MULTIPLY));
            return;
        }
        Answer correct = currentQuestion.getCorrectAnswer();
        buttonTextViewMap.forEach((K, V) -> {
            if (V.second == correct)
                K.getBackground().setColorFilter(K.getContext().getResources().getColor(R.color.green), PorterDuff.Mode.MULTIPLY);
            else
                K.getBackground().setColorFilter(K.getContext().getResources().getColor(R.color.red), PorterDuff.Mode.MULTIPLY);
        });
    }


    private void endGame() {
        // disable all buttons for answers since game ended
        // helps with retarded users
        btn_a.setEnabled(false);
        btn_b.setEnabled(false);
        btn_c.setEnabled(false);
        btn_d.setEnabled(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name");
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        builder.setView(input);
        // Set up the buttons
        builder.setPositiveButton("OK", (dialog, which) -> {
            name = input.getText().toString();
            Intent intent = new Intent(Quiz.this, Results.class);
            intent.putExtra("Result", quizGame);
            intent.putExtra("Name", name);
            startActivity(intent);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}