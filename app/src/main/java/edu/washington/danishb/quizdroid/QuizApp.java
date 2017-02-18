package edu.washington.danishb.quizdroid;

import android.app.Application;

/**
 * Created by Danish on 2/12/2017.
 */

public class QuizApp extends Application {
    
    TopicRepository topics;
    Topic currentTopic;
    int currentQuestion;
    int correctResponses;
    int lastResponse;

    @Override
    public void onCreate() {
        super.onCreate();
        topics = TopicRepository.getInstance();
    }
}
