package edu.washington.danishb.quizdroid;

/**
 * Created by Danish on 2/12/2017.
 */

public class Question {
    public String text;
    public String[] answers = new String[4];
    public int correctResponse;

    public Question(String text, String answer1, String answer2, String answer3, String answer4, int solution){
        this.text=text;
        this.answers[0] = answer1;
        this.answers[1] = answer2;
        this.answers[2] = answer3;
        this.answers[3] = answer4;
        this.correctResponse=solution;
    }
}
