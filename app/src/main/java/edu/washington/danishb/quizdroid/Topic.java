package edu.washington.danishb.quizdroid;

/**
 * Created by Danish on 2/12/2017.
 */

public class Topic {
    String topic;
    String shortDescription;
    String longDescription;
    Question[] questions;

    public Topic(String topic, String shortDescription, String longDescription, Question[] questions){
        this.topic = topic;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.questions = questions;
    }
}
