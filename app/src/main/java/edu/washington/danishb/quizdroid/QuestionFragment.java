package edu.washington.danishb.quizdroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.InvalidPropertiesFormatException;

public class QuestionFragment extends Fragment {

    QuizApp application;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("QuestionFragment", "Fragment Created");
        application = (QuizApp) getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d("QuestionFragment", "View Created");
        final Question currentQuestion = application.currentTopic.questions[application.currentQuestion-1];
        final View outerView = view;
        TextView questionView = (TextView) view.findViewById(R.id.question_view);
        questionView.setText(currentQuestion.text);
        Button response1 = (Button) view.findViewById(R.id.response1);
        response1.setText(currentQuestion.answers[0]);
        Button response2 = (Button) view.findViewById(R.id.response2);
        response2.setText(currentQuestion.answers[1]);
        Button response3 = (Button) view.findViewById(R.id.response3);
        response3.setText(currentQuestion.answers[2]);
        Button response4 = (Button) view.findViewById(R.id.response4);
        response4.setText(currentQuestion.answers[3]);

        Button submitButton = (Button) view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup responses = (RadioGroup) outerView.findViewById(R.id.response_group);
                int response = responses.getCheckedRadioButtonId();
                if(response != -1) {
                    if(response == R.id.response1){
                        application.lastResponse=0;
                    } else if(response == R.id.response2){
                        application.lastResponse=1;
                    } else if(response == R.id.response3){
                        application.lastResponse=2;
                    } else {
                        application.lastResponse=3;
                    }
                    if(application.lastResponse == currentQuestion.correctResponse){
                        application.correctResponses++;
                    }
                    FragmentTransaction tx = getFragmentManager().beginTransaction();
                    tx.replace(R.id.fragment_placeholder, new AnswerFragment());
                    tx.commit();
                }
            }
        });
    }
}
