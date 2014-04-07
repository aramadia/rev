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
import java.util.Random;

/**
 * Created by Joshua Lauer
 */
public class GameFragment extends ReactionBaseFragment {

  @InjectView(R.id.game_background) View gameBackground;
  @InjectView(R.id.start) TextView startText;
  @InjectView(R.id.average_score) TextView averageScore;
  @InjectView(R.id.number_of_tries) TextView numberOfTries;

  @Inject Timer reactionTimer;

  private static Random random = new Random();
  private static final int MIN_WAIT_TIME_MS = 1000;
  private static final int MAX_WAIT_TIME_MS = 3000;

  enum GameState {
    START_STATE,
    WAIT_STATE,
    TOO_SOON_STATE,
    CLICK_STATE,
    RESULT_STATE
  }

  private GameState gameState = GameState.START_STATE;
  private int clicks = 0;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.game_layout, container, false);

    ButterKnife.inject(this, root);

    updateState();

    return root;
  }

  private void updateState() {
    switch (gameState) {
      case START_STATE:
        updateCommonUI(R.color.neutral_background, "Tap to Start");
        reactionTimer.reset();
        break;
      case WAIT_STATE:
        updateCommonUI(R.color.wait_background, "Wait for Green");
        Handler handler = new Handler();
        handler.postDelayed(new GameRunnable(clicks), nextInt(MIN_WAIT_TIME_MS, MAX_WAIT_TIME_MS)
        );
        break;
      case TOO_SOON_STATE:
        updateCommonUI(R.color.error_background, "Too soon, try again");
        break;
      case CLICK_STATE:
        updateCommonUI(R.color.click_background, "Touch!");
        reactionTimer.startTimer();
        break;
      case RESULT_STATE:
        updateCommonUI(R.color.neutral_background, reactionTimer.getLastTimeMs() + "ms\nTouch to go again");
        break;
    }

  }

  @OnClick(R.id.game_background)
  public void gameBackgroundClick() {
    clicks++;
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
        reactionTimer.stopTimer();
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

  private void updateCommonUI(int background, String mainText) {
    setAverageScoreText(reactionTimer.getAverageTimeMs());
    setNumberOfTriesText(reactionTimer.getTimes().size());
    gameBackground.setBackgroundColor(getResources().getColor(background));
    startText.setText(mainText);
  }

  private void setAverageScoreText(long score) {
    String scoreText = "Average: " + score;
    averageScore.setText(scoreText);
    averageScore.setContentDescription(scoreText);
  }


  private void setNumberOfTriesText(int tries) {
    String scoreText = "Tries: " + tries + " / 5";
    numberOfTries.setText(scoreText);
    numberOfTries.setContentDescription(scoreText);
  }

  private int nextInt(int low, int high) {
    return random.nextInt(high - low) + low;
  }

  private class GameRunnable implements Runnable {

    private final int currentClicks;

    private GameRunnable(int clicks) {
      this.currentClicks = clicks;
    }

    @Override
    public void run() {
      if (currentClicks != clicks) {
        return;
      }
      gameState = GameState.CLICK_STATE;
      updateState();
    }

  }
}