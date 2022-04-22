package com.rocket.src.quiz;

import android.content.Context;

import com.rocket.src.dbms.QueryWrapper;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class QuizGame implements Serializable {
    Queue<QuizQuestion> questions;
    HashMap<QuizQuestion,Answer> scoreAnswers;

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
        QueryWrapper wrapper = new QueryWrapper(context);
        List<QuizQuestion> q = wrapper.getAllQuestions(language);
        int index = new Random().nextInt(q.size()-rounds); // A way to get more distributed questions
        questions = new LinkedList<>( q.subList(index,index+rounds));
        Collections.shuffle((List<?>) questions);
        scoreAnswers = new HashMap<QuizQuestion,Answer>();
    }

    public void mapAnswerToQuestion(QuizQuestion question,Answer answer){
        scoreAnswers.put(question,answer);
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
     * @return
     */
    public int calculateResults(){
        return 1; //TODO add login to result calculation
    }
}
