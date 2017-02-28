package edu.washington.danishb.quizdroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Danish on 2/12/2017.
 */

public class TopicRepository {

    public static TopicRepository instance = new TopicRepository();
    public static TopicRepository getInstance(){
        return(instance);
    };

    Map<String, Topic> topics;

    public TopicRepository() {
        this.topics = new HashMap<String, Topic>();
        if(QuizApp.JSON.exists()){
            processJSON();
        } else {
            new FirstTimeLoad().execute();
        }
    }

    public Topic getTopic(String name){
        return(topics.get(name));
    }

    public String[] getTopics(){
        String[] ret = new String[topics.size()];
        int i = 0;
        for(String topic : topics.keySet()){
            ret[i] = topic;
            i++;
        }
        return ret;
    }

    public Set<String> getTopicList(){
        return(topics.keySet());
    }

    private void processJSON() {
        JSONArray data = new JSONArray();
        try {
            FileInputStream fis = new FileInputStream(QuizApp.JSON);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            data = new JSONArray(sb.toString());

            for(int i=0; i< data.length(); i++) {
                JSONObject json_topic = data.getJSONObject(i);
                String title = json_topic.getString("title");
                String desc = json_topic.getString("desc");
                JSONArray json_questions = json_topic.getJSONArray("questions");
                Question[] questions = new Question[json_questions.length()];
                for(int j = 0; j < questions.length; j++) {
                    JSONObject q = json_questions.getJSONObject(j);
                    String text = q.getString("text");
                    int answer = Integer.parseInt(q.getString("answer")) - 1;
                    JSONArray responses = q.getJSONArray("answers");
                    questions[j] = new Question(text, responses.getString(0), responses.getString(1),
                            responses.getString(2), responses.getString(3), answer);
                }
                topics.put(title, new Topic(title, desc, desc, questions));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private class FirstTimeLoad extends AsyncTask<String, String, String>{
        HttpURLConnection conn;
        URL url = null;


        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://tednewardsandbox.site44.com/questions.json");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                FileOutputStream outputStream = new FileOutputStream(QuizApp.JSON);
                outputStream.write(result.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
                processJSON();
            Intent restart = new Intent(QuizApp.currentContext, TopicSelectActivity.class);
            QuizApp.currentContext.startActivity(restart);
            }

        }
    }
