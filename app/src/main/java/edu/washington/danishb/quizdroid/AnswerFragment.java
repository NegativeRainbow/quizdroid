package edu.washington.danishb.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class AnswerFragment extends Fragment {

    QuizApp application;

    public AnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("AnswerFragment", "Fragment Created");
        application = (QuizApp) getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_answer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d("AnswerFragment", "View Created");
        Question currentQuestion = application.currentTopic.questions[application.currentQuestion-1];
        TextView questionView = (TextView) view.findViewById(R.id.answer_question_view);
        questionView.setText(currentQuestion.text);

        TextView userResponse = (TextView) view.findViewById(R.id.user_response);
        userResponse.setText("Your response was: " + currentQuestion.answers[application.lastResponse]);

        TextView correctResponse = (TextView) view.findViewById(R.id.correct_response);
        correctResponse.setText("The correct answer was: " + currentQuestion.answers[currentQuestion.correctResponse]);

        TextView currentScore = (TextView) view.findViewById(R.id.current_score);
        currentScore.setText("You have " + application.correctResponses + " out of " + application.currentQuestion + " correct");
        Button nextButton = (Button) view.findViewById(R.id.next_question);
        if(application.currentQuestion == application.currentTopic.questions.length) {
            nextButton.setText("Finish");
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(), TopicSelectActivity.class));
                }
            });
        } else {
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    application.currentQuestion++;
                    FragmentTransaction tx = getFragmentManager().beginTransaction();
                    tx.replace(R.id.fragment_placeholder, new QuestionFragment());
                    tx.commit();
                }
            });
        }
    }
}
