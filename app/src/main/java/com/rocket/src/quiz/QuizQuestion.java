package com.rocket.src.quiz;

import android.media.Image;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class QuizQuestion implements Serializable {
    private final String question;
    private Image image;
    private List<Answer> answers;

    public QuizQuestion(String question, Image image, List<Answer> answers) {
        this.question = question;
        this.image = image;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
    public Answer getCorrectAnswer(){
        for(Answer answer : answers)
            if(answer.isCorrect()) return answer;
        return null; // unreachable code unless you fuck something up
    }
    @NonNull
    @Override
    public String toString() {
        return "QuizQuestion{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }
}
