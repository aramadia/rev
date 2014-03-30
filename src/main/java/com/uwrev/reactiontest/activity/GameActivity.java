package com.uwrev.reactiontest.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.uwrev.reactiontest.ForApplication;
import com.uwrev.reactiontest.R;
import com.uwrev.reactiontest.Timer;

import javax.inject.Inject;
import java.util.Random;

public class GameActivity extends ReactionBaseActivity {

  @Inject @ForApplication Context application;
  @Inject Timer reactionTimer;

  private static Random random = new Random();

  private Button gameButton, scoreButton;
  private LinearLayout gameLayout;
  private TextView gameTime;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.game);

    gameTime = (TextView) findViewById(R.id.game_time);

    gameLayout = ((LinearLayout) findViewById(R.id.game_background));
    gameLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        reactionTimer.stopTimer();
        gameTime.setText(reactionTimer.getLastTimeMs() + "ms");
      }
    });

    gameButton = ((Button) findViewById(R.id.start_game));
    gameButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
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
    });

    scoreButton = ((Button) findViewById(R.id.button_score));
    scoreButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(GameActivity.this, ScoreActivity.class));
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  private int nextInt(int low, int high) {
    return random.nextInt(high - low) + low;
  }

}
