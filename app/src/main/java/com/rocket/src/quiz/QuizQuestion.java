package com.rocket.src.quiz;

import android.media.Image;

import java.util.List;

public class QuizQuestion {
    private Difficulty difficulty;
    private final String question;
    private Image image;
    private List<Answer> answers;

    public QuizQuestion(String question, Image image, List<Answer> answers) {
        this.question = question;
        this.image = image;
        this.answers = answers;
        difficulty = Difficulty.MEDIUM;
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
}
