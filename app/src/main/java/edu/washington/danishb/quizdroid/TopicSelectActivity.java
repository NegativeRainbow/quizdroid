package edu.washington.danishb.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Set;

public class TopicSelectActivity extends AppCompatActivity {

    QuizApp application;
    String[] topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_select);
        application = (QuizApp) getApplication();
        ListView topicList = (ListView) findViewById(R.id.activity_topic_select);
        topics = application.topics.getTopics();
        ArrayAdapter<String> topicAdapter= new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                topics);
        topicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                application.currentTopic = application.topics.getTopic(topics[i]);
                startActivity(new Intent(TopicSelectActivity.this, QuizActivity.class));
            }
        });
        topicList.setAdapter(topicAdapter);
    }
}
