package com.rocket.src.quiz;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.rocket.src.dbms.QueryWrapper;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class QuizGame implements Serializable {
    private Queue<QuizQuestion> questions;
    private HashMap<QuizQuestion,Answer> scoreAnswers;
    private int rounds;
    public int CORRECT=0;
    public int WRONG = 0;

    public HashMap<QuizQuestion, Answer> getScoreAnswers() {
        return scoreAnswers;
    }
    /**
     * Starts a quiz game with a number of rounds
     *
     * @param rounds The number of rounds to play
     * @param language The programming language for theme of the questions
     */
    public QuizGame(String language, int rounds, Context context) {
        this.rounds = rounds;
        QueryWrapper wrapper = new QueryWrapper(context);
        Log.d("ROUNDS",String.valueOf(rounds));
        List<QuizQuestion> q = wrapper.getAllQuestions(language);
        Collections.shuffle(q);
        questions = new LinkedList<>(q.subList(0,rounds));
        scoreAnswers = new HashMap<QuizQuestion,Answer>();
    }
    public void mapAnswerToQuestion(QuizQuestion question,Answer answer){
        scoreAnswers.put(question,answer);
        if(question.getCorrectAnswer().equals(answer))
            CORRECT++;
        else
            WRONG++;
    }
    /**
     * API call to get the next QuizQuestion and remove it from the Queue. Internally uses `poll()`
     * so no exceptions are thrown if the Queue is empty
     * @return the next question
     */
    public QuizQuestion nextQuestion() {
        return questions.poll();
    }

    /**
     * Should be called after the game is finished. The method returns the
     * results for the current game
     * @return a percentage depicting the results for a game
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public float calculateResults(){
        final Integer[] score = {0};
        scoreAnswers.forEach((K,V)->{
            if(K.getCorrectAnswer().equals(V)) score[0]++;
        });
        Log.d("CALCULATE", String.valueOf(score[0]));
        return score[0]/(float)rounds;
    }
}
