package com.uwrev.reactiontest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.uwrev.reactiontest.R;
import com.uwrev.reactiontest.ReactionApplication;
import com.uwrev.reactiontest.UserManager;
import com.uwrev.reactiontest.adapter.ScoreAdapter;
import com.uwrev.reactiontest.model.RevScore;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by Joshua Lauer
 */
public class ScoreFragment extends Fragment {

  @InjectView(R.id.score_list) ListView scoreList;
  @InjectView(R.id.progress_spinner) ProgressBar progressSpinner;
  @Inject UserManager userManager;

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

  @OnClick(R.id.button_add_score)
  public void addScore() {
    // Lets submit a dummy score here too.
    RevScore highScore = new RevScore();

    highScore.setScore((int)(Math.random() * 1000));
    highScore.setDate(new Date());
    highScore.setGameMode("default");

    userManager.reportScore(highScore);
  }


  private void getScoresAsync() {
    scoreList.setVisibility(View.GONE);
    progressSpinner.setVisibility(View.VISIBLE);

    ParseQuery<RevScore> query = ParseQuery.getQuery("RevScore");
    query.include(RevScore.USER);
    query.findInBackground(new FindCallback<RevScore>() {
      public void done(List<RevScore> revScores, ParseException e) {
        if (e == null) {
          Log.d("score", "Retrieved " + revScores.size() + " scores");
          progressSpinner.setVisibility(View.GONE);
          scoreList.setVisibility(View.VISIBLE);
          adapter.setScores(revScores);
        } else {
          Log.e("score", "Error: " + e.getMessage());
        }
      }
    });
  }

}