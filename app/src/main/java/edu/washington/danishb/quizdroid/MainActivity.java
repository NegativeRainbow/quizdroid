package edu.washington.danishb.quizdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.topicList);

        String[] topics = new String[] {
                "Math",
                "Physics",
                "Marvel Super Heroes"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                topics);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String topic = (String) listView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, Question.class);
                intent.putExtra("TOPIC", topic);
                startActivity(intent);
            }
        });

        listView.setAdapter(adapter);
    }
}
