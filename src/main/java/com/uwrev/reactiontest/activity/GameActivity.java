package com.uwrev.reactiontest.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.uwrev.reactiontest.ForApplication;
import com.uwrev.reactiontest.R;
import com.uwrev.reactiontest.Timer;

import javax.inject.Inject;
import java.util.Random;

public class GameActivity extends ReactionBaseActivity {

  @Inject @ForApplication Context application;
  @Inject Timer reactionTimer;

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
    gameTime.setText(reactionTimer.getLastTimeMs() + "ms");
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
    startActivity(new Intent(GameActivity.this, ScoreActivity.class));
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  private int nextInt(int low, int high) {
    return random.nextInt(high - low) + low;
  }

}
