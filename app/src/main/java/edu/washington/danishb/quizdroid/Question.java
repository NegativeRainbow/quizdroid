package edu.washington.danishb.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class Question extends AppCompatActivity {

    private class Quiz {
        String[][] questionsResponses;
        int[] correctResponses;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent intent = getIntent();
        String topic = intent.getStringExtra("Topic");
        TextView viewTopic = (TextView)findViewById(R.id.viewTopic);
        viewTopic.setText(topic);
        Log.d("Quiz", topic);

        String[][] mathQuestions = {
                {"1+1", "2", "3", "1", "4"},
                {"2+2", "2", "3", "1", "4"},
                {"2+1", "2", "3", "1", "4"}
        };

        int[] mathAnswers = {1, 4, 2};

        Map<String, String[][]> allQuestions = new HashMap<>();
        allQuestions.put("Math", mathQuestions);

        Map<String, int[]> allAnswers = new HashMap<>();
        allAnswers.put("Math", mathAnswers);

        Map<String, String> topicOverviews = new HashMap<>();
        topicOverviews.put("Math", "Math is the thing where you add and stuff");

        Quiz quiz = new Quiz();
        quiz.questionsResponses = allQuestions.get(topic);
        quiz.correctResponses = allAnswers.get(topic);
    }
}