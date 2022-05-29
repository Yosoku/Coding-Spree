package com.rocket.src.quiz;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class representing an Answer to a Quiz application,the Answer has 2
 * attributes about its text and a boolean that holds true if he answer is
 * indeed the correct answer
 */
public class Answer implements Serializable {
    private final String answer;
    private boolean isCorrect;

    /**
     * Initialize an Answer instance with the text and the correct attribute
     * @param answer The text
     * @param isCorrect True if its correct,false if its not
     */
    public Answer(String answer, boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }
    /**
     * This costructor should be used to initialize false answers. Sets the
     * correct attribute internally to false
     * @param answer The answer text
     */
    public Answer(String answer) {
        this.answer = answer;
        this.isCorrect = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer1 = (Answer) o;
        return answer.equals(answer1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer);
    }

    public String getAnswer() {
        return answer;
    }

    @NonNull
    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
