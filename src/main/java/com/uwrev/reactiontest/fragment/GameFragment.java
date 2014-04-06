package com.uwrev.reactiontest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.uwrev.reactiontest.R;
import com.uwrev.reactiontest.Timer;

import javax.inject.Inject;

/**
 * Created by Joshua Lauer
 */
public class GameFragment extends ReactionBaseFragment {

  @InjectView(R.id.game_background) View gameBackground;
  @InjectView(R.id.start) TextView startText;
  @InjectView(R.id.average_score) TextView averageScore;
  @InjectView(R.id.number_of_tries) TextView numberOfTries;

  @Inject Timer reactionTimer;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.game_layout, container, false);

    ButterKnife.inject(this, root);

    averageScore.setText("");
    numberOfTries.setText("");
    startText.setVisibility(View.VISIBLE);
    startText.setText("Tap to Start");

    return root;
  }

  @OnClick(R.id.game_background)
  public void gameBackgroundClick() {
    // do the logic here
  }

}