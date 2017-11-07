package com.example.christofferwiregren.quiz;

/**
 * Created by christofferwiregren on 2017-11-02.
 */

public class Card {
    private String rubrik;
    private String text;
    private String statement1;
    private String statement2;
    private String statement3;
    private String statement4;
    private int correctAnswer;

    public Card(String rubrik, String text, String statement1, String statement2, String statement3, String statement4, int correctAnswer) {
        this.rubrik = rubrik;
        this.text = text;
        this.statement1 = statement1;
        this.statement2 = statement2;
        this.statement3 = statement3;
        this.statement4 = statement4;
        this.correctAnswer = correctAnswer;
    }


    public String getRubrik() {
        return rubrik;
    }

    public void setRubrik(String rubrik) {
        this.rubrik = rubrik;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatement1() {
        return statement1;
    }

    public void setStatement1(String statement1) {
        this.statement1 = statement1;
    }

    public String getStatement2() {
        return statement2;
    }

    public void setStatement2(String statement2) {
        this.statement2 = statement2;
    }

    public String getStatement3() {
        return statement3;
    }

    public void setStatement3(String statement3) {
        this.statement3 = statement3;
    }

    public String getStatement4() {
        return statement4;
    }

    public void setStatement4(String statement4) {
        this.statement4 = statement4;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}