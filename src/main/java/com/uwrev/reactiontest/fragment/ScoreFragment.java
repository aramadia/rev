package com.uwrev.reactiontest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.uwrev.reactiontest.R;
import com.uwrev.reactiontest.adapter.ScoreAdapter;
import com.uwrev.reactiontest.model.RevScore;

import java.util.List;

/**
 * Created by Joshua Lauer
 */
public class ScoreFragment extends Fragment {

  @InjectView(R.id.score_list) ListView scoreList;
  private ScoreAdapter adapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.score_layout, container, false);

    ButterKnife.inject(this, root);

    adapter = new ScoreAdapter(getActivity());
    scoreList.setAdapter(adapter);

    getScoresAsync();

    return root;
  }

  private void getScoresAsync() {
    ParseQuery<RevScore> query = ParseQuery.getQuery("RevScore");
    query.include(RevScore.USER);
    query.findInBackground(new FindCallback<RevScore>() {
      public void done(List<RevScore> scoreList, ParseException e) {
        if (e == null) {
          Log.d("score", "Retrieved " + scoreList.size() + " scores");
          adapter.setScores(scoreList);
        } else {
          Log.e("score", "Error: " + e.getMessage());
        }
      }
    });
  }

}