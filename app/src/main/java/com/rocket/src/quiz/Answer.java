package com.rocket.src.quiz;

public class Answer {
    private final String answer;
    boolean isCorrect;

    public Answer(String answer) {
        this.answer = answer;
        this.isCorrect = false;
    }
    public Answer(String answer, boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
