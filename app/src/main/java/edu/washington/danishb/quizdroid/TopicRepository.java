package edu.washington.danishb.quizdroid;

import android.os.Environment;
import android.util.JsonReader;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        topics = new HashMap<String, Topic>();
        File sdcard = Environment.getExternalStorageDirectory();
        File questionFile = new File(sdcard, "questions.json");
        Log.v("TopicRepository", questionFile.toString());
        try {
            FileReader questionReader = new FileReader(questionFile);
            JsonReader reader = new JsonReader(questionReader);
            try {
                reader.beginArray();
                while(reader.hasNext()){
                    reader.nextName();
                    String topicName = reader.nextString();
                    Log.v("TopicRepository", topicName);
                    reader.nextName();
                    String topicDesc = reader.nextString();
                    Log.v("TopicRepository", topicDesc);
                    reader.nextName();
                    reader.beginArray();
                    while(reader.hasNext()){
                        reader.nextName();
                        String questionText = reader.nextString();
                        Log.v("TopicRepository", questionText);
                        reader.nextName();
                        int questionCorrect = reader.nextInt()-1;
                        reader.nextName();
                        List<Question> questionList = new ArrayList<Question>();
                        reader.beginArray();
                        questionList.add(new Question(questionText, reader.nextString(), reader.nextString(),
                                reader.nextString(), reader.nextString(), questionCorrect));
                        reader.endArray();
                        Question[] questionArray = new Question[questionList.size()];
                        int i = 0;
                        for(Question q : questionList){
                            questionArray[i] = q;
                            i++;
                        }
                        topics.put(topicName, new Topic(topicName, topicDesc, topicDesc, questionArray));
                    }
                    reader.endArray();
                }
                reader.endArray();
                reader.close();
            } catch(IOException e){
                Log.e("TopicRepository", e.getMessage());
            }
        } catch(FileNotFoundException e) {
            Log.e("QuestionRepository", e.getMessage());
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
}
