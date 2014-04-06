package com.uwrev.reactiontest.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.uwrev.reactiontest.R;
import com.uwrev.reactiontest.Timer;
import com.uwrev.reactiontest.UserManager;
import com.uwrev.reactiontest.model.RevScore;

import javax.inject.Inject;
import java.util.Date;
import java.util.Random;

public class GameActivity extends ReactionBaseActivity {

  @Inject Timer reactionTimer;
  @Inject UserManager userManager;

  private static Random random = new Random();

  @InjectView(R.id.game_background) LinearLayout gameLayout;
  @InjectView(R.id.game_time) TextView gameTime;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game);

    ButterKnife.inject(this);
  }

  @OnClick(R.id.game_background)
  public void updateScore() {
    reactionTimer.stopTimer();
    gameTime.setText("Average " + reactionTimer.getAverageTimeMs() + "ms");
  }

  @OnClick(R.id.button_start_game)
  public void startGame() {
    gameLayout.setBackgroundColor(Color.BLACK);
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        gameLayout.setBackgroundColor(Color.BLUE);
        reactionTimer.startTimer();
      }
    }, nextInt(1000, 10000));

  }

  @OnClick(R.id.button_score)
  public void showScore() {
    // Lets submit a dummy score here too.
    RevScore highScore = new RevScore();

    highScore.setScore((int)(Math.random() * 1000));
    highScore.setDate(new Date());
    highScore.setGameMode("default");

    userManager.reportScore(highScore);
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  private int nextInt(int low, int high) {
    return random.nextInt(high - low) + low;
  }

}
