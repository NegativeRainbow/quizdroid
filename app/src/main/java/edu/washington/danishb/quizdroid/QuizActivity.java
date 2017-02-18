package edu.washington.danishb.quizdroid;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    QuizApp application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        application = (QuizApp) getApplication();
        application.currentQuestion = 0;
        application.correctResponses = 0;
        application.lastResponse = -1;
        TextView topicView = (TextView) findViewById(R.id.current_topic_view);
        topicView.setText(application.currentTopic.topic);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_placeholder, new TopicOverviewFragment());
        tx.commit();
    }
}
