package com.uwrev.reactiontest.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.uwrev.reactiontest.R;
import com.uwrev.reactiontest.ReactionTimer;

import java.util.Random;

public class GameActivity extends Activity {

  private static Random random = new Random();
  private static ReactionTimer reactionTimer = new ReactionTimer();

  private Button gameButton;
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
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  private int nextInt(int low, int high) {
    return random.nextInt(high - low) + low;
  }

}
