package com.rocket.src.dbms;

import android.content.Context;
import android.database.Cursor;

import com.rocket.src.quiz.Answer;
import com.rocket.src.quiz.QuizQuestion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class wraps querying as well as handling dbms operations such a open
 * and close. Extend to add more queries as you need. Currently offers an API
 * to retrieve and construct all Quiz Questions in the database
 */
public class QueryWrapper {
    private final QuizDatabase database;

    public QueryWrapper(Context context) {
        database = new QuizDatabase(context);
        try {
            database.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A wrapper method around querying the dbms for quiz questions. The
     * method constructs a List of Quiz questions and each quiz question
     * individually
     * @param language The programming language you are querying for
     * @return A list of all the quiz questions for a programming language,or
     * NULL if the database failed to open
     */
    public List<QuizQuestion> getAllQuestions(String language) {
        if (!database.openDataBase()) return null;
        List<QuizQuestion> questions = new ArrayList<>();
        Cursor cursor = database.runQuery("SELECT * FROM QUESTIONS JOIN " +
                "ANSWERS WHERE ANSWERS.answer_id==QUESTIONS.answer_id AND " +
                "language==?", new String[]{language});
        // Returns a snapshot of qID|aID|question|language|pathImage|answer_id|correctAns|ansB|ansC|ansD
        String question, correct_answer, answer_b, answer_c, answer_d;
        // TODO break this shitty code in methods
        while (cursor.moveToNext()) {
            List<Answer> answers = new ArrayList<>();
            language = cursor.getString(cursor.getColumnIndexOrThrow(
                    "language"));
            question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
            correct_answer = cursor.getString(cursor.getColumnIndexOrThrow("correct_answer"));
            answer_b = cursor.getString(cursor.getColumnIndexOrThrow("answer_b"));
            answer_c = cursor.getString(cursor.getColumnIndexOrThrow("answer_c"));
            answer_d = cursor.getString(cursor.getColumnIndexOrThrow("answer_d"));
            Answer correct = new Answer(correct_answer, true);
            Answer b = new Answer(answer_b);
            Answer c = new Answer(answer_c);
            Answer d = new Answer(answer_d);
            answers.add(correct);
            answers.add(b);
            answers.add(c);
            answers.add(d);
            Collections.shuffle(answers);
            questions.add(new QuizQuestion(question, null, answers));

        }
        database.close();
        return questions;
    }
}
