package com.uwrev.reactiontest.fragment;

import android.os.Bundle;
import android.os.Handler;
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

  enum GameState {
    START_STATE,
    WAIT_STATE,
    TOO_SOON_STATE,
    CLICK_STATE,
    RESULT_STATE
  }

  private GameState gameState;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.game_layout, container, false);

    ButterKnife.inject(this, root);

    gameState = GameState.START_STATE;

    updateState();

    return root;
  }

  private void updateState() {
    switch (gameState) {
      case START_STATE:
//        averageScore.setText("");
//        numberOfTries.setText("");
//        startText.setVisibility(View.VISIBLE);
//        startText.setText("Tap to Start");
//        reactionTimer.reset();
        break;
      case WAIT_STATE:
        Handler handler = new Handler();
        handler.postDelayed(
            new Runnable() {
              @Override
              public void run() {
                gameState = GameState.CLICK_STATE;
                updateState();
              }
            }, 1000
        );

        break;
      case TOO_SOON_STATE:
        break;
      case CLICK_STATE:
        break;
      case RESULT_STATE:
        break;
    }

  }

  @OnClick(R.id.game_background)
  public void gameBackgroundClick() {
    switch (gameState) {
      case START_STATE:
        gameState = GameState.WAIT_STATE;
        break;
      case WAIT_STATE:
        gameState = GameState.TOO_SOON_STATE;
        break;
      case TOO_SOON_STATE:
        gameState = GameState.WAIT_STATE;
        break;
      case CLICK_STATE:
        gameState = GameState.RESULT_STATE;
        break;
      case RESULT_STATE:
        gameState = GameState.WAIT_STATE;
        break;
    }
    updateState();

  }

  public GameState getGameState() {
    return gameState;
  }
}