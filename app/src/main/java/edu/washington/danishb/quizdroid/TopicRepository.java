package edu.washington.danishb.quizdroid;

import java.util.HashMap;
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
        Question[] mathQuestions = new Question[4];
        mathQuestions[0] = new Question("1+1=", "1", "2", "3", "0", 1);
        mathQuestions[1] = new Question("1-1=", "1", "2", "3", "0", 3);
        mathQuestions[2] = new Question("1*1=", "1", "2", "3", "0", 0);
        mathQuestions[3] = new Question("1/1=", "1", "2", "3", "0", 0);
        Topic math = new Topic("Math", "Basic Math Questions", "Test your math skills with questions" +
                "on addition, subtraction, multiplication, and division!", mathQuestions);
        topics.put("Math", math);
        Question[] physicsQuestions = new Question[3];
        physicsQuestions[0] = new Question("e=", "2.1 something", "mc^2", "pi", "elephant", 1);
        physicsQuestions[1] = new Question("Every action has an equal and opposite",
                "force", "consequence", "equation", "reaction", 3);
        physicsQuestions[2] = new Question("Without atmosphere, a bowling ball will fall _____ than a feather"
                , "at the same speed", "faster", "slower", "unlike", 0);
        Topic physics = new Topic("Physics", "Basic Physics Questions", "Test your physics skills with" +
                "questions on basic theories!", physicsQuestions);
        topics.put("Physics", physics);
        Question[] marvelQuestions = new Question[3];
        marvelQuestions[0] = new Question("What is Wolverine's distinguishing feature", "Hat",
                "Mask", "Voice", "Claws", 3);
        marvelQuestions[1] = new Question("Which one of these is a Marvel character",
                "Batman", "Superman", "Doomsday", "Dr. Doom", 3);
        marvelQuestions[2] = new Question("Whens", "Marvel", "Mahvel", "The next comic coming out",
                "The next Avengers movie", 1);
        Topic marvel = new Topic("Marvel Super Heroes", "Basic Marvel Questions", "I don't know much" +
                "about Marvel, so these are super basic questions on Marvel.", physicsQuestions);
        topics.put("Marvel Super Heroes", marvel);
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
