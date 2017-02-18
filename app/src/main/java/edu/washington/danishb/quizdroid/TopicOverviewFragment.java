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
import android.widget.TextView;

public class TopicOverviewFragment extends Fragment {

    private QuizApp application;

    public TopicOverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TopicOverviewFragment", "Fragment Created");
        application = (QuizApp) getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topic_overview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d("TopicOverviewFragment", application.currentTopic.longDescription);
        TextView topicDescription = (TextView) view.findViewById(R.id.topic_description_view);
        topicDescription.setText(application.currentTopic.longDescription);
        Button beginButton = (Button) view.findViewById(R.id.begin_quiz_button);
        beginButton.setOnClickListener(new View.OnClickListener() {
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
