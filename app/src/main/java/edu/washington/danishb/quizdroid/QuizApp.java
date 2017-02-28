package edu.washington.danishb.quizdroid;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Danish on 2/12/2017.
 */

public class QuizApp extends Application {

    static Context currentContext;
    TopicRepository topics;
    Topic currentTopic;
    int currentQuestion;
    int correctResponses;
    int lastResponse;
    static File JSON;

    @Override
    public void onCreate() {
        super.onCreate();
        JSON = new File(getFilesDir(), "questions.json");
        topics = TopicRepository.getInstance();
    }

    public void setContext(Context c){
        this.currentContext = c;
    }

    public Context getContext(){
        return currentContext;
    }
}
